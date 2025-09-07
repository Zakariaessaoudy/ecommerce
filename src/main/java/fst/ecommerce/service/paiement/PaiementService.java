package fst.ecommerce.service.paiement;

import fst.ecommerce.dto.paiement.PaiementDto;
import fst.ecommerce.entity.Paiement;
import fst.ecommerce.service.CrudDtoService;

import java.util.Map;

public interface PaiementService extends CrudDtoService<Paiement, PaiementDto> {
    Paiement create(Paiement paiement);
    Paiement update(Long id, Paiement paiement);

    // Real payments API (Stripe PaymentIntent style)
    Paiement initierPaiementAvecStripe(Long commandeId, String currency);
    Paiement confirmerPaiementStripe(String paymentIntentId);
    Paiement annulerPaiementStripe(String paymentIntentId);

    // Optionnel: récupération par références externes
    Paiement obtenirParPaymentId(String paymentId);
    Paiement obtenirParReferenceTransaction(String transactionReference);
}
