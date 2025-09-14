package fst.ecommerce.service.paiement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fst.ecommerce.entity.Commande;
import fst.ecommerce.entity.Paiement;
import fst.ecommerce.entity.Produit;
import fst.ecommerce.enums.MethodePaiement;
import fst.ecommerce.enums.StatutPaiement;
import fst.ecommerce.repository.CommandeRepository;
import fst.ecommerce.repository.PaiementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

@Service
@Transactional
public class PaiementServiceImpl  {

    /*private final PaiementRepository paiementRepository;
    private final CommandeRepository commandeRepository;
    private final ObjectMapper objectMapper;

    @Value("${stripe.secret:}")
    private String configuredStripeSecret;

    public PaiementServiceImpl(PaiementRepository paiementRepository,
                               CommandeRepository commandeRepository) {
        this.paiementRepository = paiementRepository;
        this.commandeRepository = commandeRepository;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Paiement create(Paiement paiement) {
        if (paiement.getMethode() == MethodePaiement.STRIPE && paiement.getCommande() != null) {
            Long commandeId = paiement.getCommande().getId();
            String currency = paiement.getCurrency() != null ? paiement.getCurrency() : "usd";
            return initierPaiementAvecStripe(commandeId, currency);
        }
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement update(Long id, Paiement paiement) {
        Paiement existing = paiementRepository.findById(id).orElse(null);
        if (existing == null) return null;

        if (paiement.getMontant() != null) existing.setMontant(paiement.getMontant());
        if (paiement.getCurrency() != null) existing.setCurrency(paiement.getCurrency());
        if (paiement.getStatut() != null) existing.setStatut(paiement.getStatut());
        if (paiement.getMethode() != null) existing.setMethode(paiement.getMethode());
        if (paiement.getTransactionReference() != null) existing.setTransactionReference(paiement.getTransactionReference());
        if (paiement.getCommande() != null) existing.setCommande(paiement.getCommande());
        // Ne pas modifier paymentId manuellement si déjà lié à un provider

        return paiementRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        paiementRepository.deleteById(id);
    }

    @Override
    public Paiement getById(Long id) {
        return paiementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Paiement> getAll() {
        return paiementRepository.findAll();
    }

    @Override
    public Paiement initierPaiementAvecStripe(Long commandeId, String currency) {
        Commande commande = commandeRepository.findById(commandeId).orElse(null);
        if (commande == null) throw new IllegalArgumentException("Commande introuvable: " + commandeId);

        BigDecimal montantTotal = calculerMontantCommande(commande);
        long amountInMinor = montantTotal.movePointRight(2).setScale(0, RoundingMode.HALF_UP).longValueExact();

        String transactionRef = genererReferenceTransaction(commandeId);
        Map<String, String> form = new LinkedHashMap<>();
        form.put("amount", String.valueOf(amountInMinor));
        form.put("currency", currency.toLowerCase(Locale.ROOT));
        form.put("automatic_payment_methods[enabled]", "true");
        form.put("metadata[commandeId]", String.valueOf(commandeId));
        form.put("metadata[transactionReference]", transactionRef);
        form.put("description", "Commande #" + commandeId);

        Map<String, Object> intent = appelerStripe("https://api.stripe.com/v1/payment_intents", form);

        Paiement paiement = new Paiement();
        paiement.setPaymentId((String) intent.get("id"));
        paiement.setTransactionReference(transactionRef);
        paiement.setClientSecret((String) intent.get("client_secret"));
        paiement.setMontant(montantTotal);
        paiement.setCurrency(currency.toUpperCase(Locale.ROOT));
        paiement.setStatut(mapperStatutStripe((String) intent.get("status")));
        paiement.setMethode(MethodePaiement.STRIPE);
        paiement.setCommande(commande);

        Paiement saved = paiementRepository.save(paiement);
        commande.setPaiement(saved);
        commandeRepository.save(commande);
        return saved;
    }

    @Override
    public Paiement confirmerPaiementStripe(String paymentIntentId) {
        Paiement paiement = paiementRepository.findByPaymentId(paymentIntentId).orElse(null);
        if (paiement == null) throw new IllegalArgumentException("Paiement introuvable pour paymentIntentId: " + paymentIntentId);

        Map<String, Object> intent = appelerStripe("https://api.stripe.com/v1/payment_intents/" + urlEncode(paymentIntentId) + "/confirm", Collections.emptyMap());
        paiement.setStatut(mapperStatutStripe((String) intent.get("status")));
        paiement.setClientSecret((String) intent.get("client_secret"));
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement annulerPaiementStripe(String paymentIntentId) {
        Paiement paiement = paiementRepository.findByPaymentId(paymentIntentId).orElse(null);
        if (paiement == null) throw new IllegalArgumentException("Paiement introuvable pour paymentIntentId: " + paymentIntentId);

        Map<String, Object> intent = appelerStripe("https://api.stripe.com/v1/payment_intents/" + urlEncode(paymentIntentId) + "/cancel", Collections.emptyMap());
        paiement.setStatut(mapperStatutStripe((String) intent.get("status")));
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement obtenirParPaymentId(String paymentId) {
        return paiementRepository.findByPaymentId(paymentId).orElse(null);
    }

    @Override
    public Paiement obtenirParReferenceTransaction(String transactionReference) {
        return paiementRepository.findByTransactionReference(transactionReference).orElse(null);
    }

    private BigDecimal calculerMontantCommande(Commande commande) {
        Collection<Produit> produits = commande.getProduits();
        if (produits == null || produits.isEmpty()) return BigDecimal.ZERO;
        double total = produits.stream().mapToDouble(Produit::getPrix).sum();
        return BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP);
    }

    private String genererReferenceTransaction(Long commandeId) {
        return "CMD-" + commandeId + "-" + Instant.now().getEpochSecond() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(Locale.ROOT);
    }

    private Map<String, Object> appelerStripe(String url, Map<String, String> formFields) {
        try {
            String apiKey = obtenirStripeSecretKey();
            if (apiKey == null || apiKey.isBlank()) {
                throw new IllegalStateException("Clé secrète Stripe manquante. Définir STRIPE_SECRET_KEY ou property stripe.secret");
            }

            String body = encoderForm(formFields);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IllegalStateException("Erreur Stripe " + response.statusCode() + ": " + response.body());
            }

            return objectMapper.readValue(response.body(), new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Appel Stripe échoué: " + e.getMessage(), e);
        }
    }

    private String obtenirStripeSecretKey() {
        String envKey = System.getenv("STRIPE_SECRET_KEY");
        if (envKey != null && !envKey.isBlank()) return envKey;
        if (configuredStripeSecret != null && !configuredStripeSecret.isBlank()) return configuredStripeSecret;
        return null;
    }

    private StatutPaiement mapperStatutStripe(String stripeStatus) {
        if (stripeStatus == null) return StatutPaiement.PENDING;
        switch (stripeStatus) {
            case "succeeded":
                return StatutPaiement.SUCCESS;
            case "canceled":
                return StatutPaiement.CANCELLED;
            case "requires_payment_method":
            case "requires_confirmation":
            case "requires_action":
            case "processing":
            case "requires_capture":
            default:
                return StatutPaiement.PENDING;
        }
    }

    private String encoderForm(Map<String, String> form) {
        if (form.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : form.entrySet()) {
            if (sb.length() > 0) sb.append('&');
            sb.append(urlEncode(entry.getKey())).append('=').append(urlEncode(entry.getValue()));
        }
        return sb.toString();
    }

    private String urlEncode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
*/}
