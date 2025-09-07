package fst.ecommerce.dto.paiement;

import fst.ecommerce.enums.MethodePaiement;
import fst.ecommerce.enums.StatutPaiement;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaiementDto {
    private Long id;
    private String paymentId;
    private String transactionReference;
    private String clientSecret;
    private BigDecimal montant;
    private String currency;
    private StatutPaiement statut;
    private MethodePaiement methode;
    private LocalDateTime datePaiement;
    private Long commandeId; // éviter de renvoyer tout l’objet Commande
}

