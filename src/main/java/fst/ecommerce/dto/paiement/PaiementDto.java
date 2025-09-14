package fst.ecommerce.dto.paiement;

import fst.ecommerce.enums.MethodePaiement;
import fst.ecommerce.enums.StatutPaiement;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaiementDto {
    private String id;
    private String paymentId;
    private String transactionReference;
    private String clientSecret;
    private BigDecimal montant;
    private String currency;
    private StatutPaiement statut;
    private MethodePaiement methode;
    private LocalDateTime datePaiement;
    private String commandeId; // éviter de renvoyer tout l’objet Commande
}

