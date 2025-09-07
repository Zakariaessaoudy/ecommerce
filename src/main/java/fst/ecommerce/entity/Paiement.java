package fst.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import fst.ecommerce.enums.MethodePaiement;
import fst.ecommerce.enums.StatutPaiement;
import java.math.BigDecimal;

@Entity
@Table(name = "paiements")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentId; // ID venant de Stripe/PayPal
    private String transactionReference; // Référence unique interne

    @Transient
    private String clientSecret; // Non persisté: utilisé par le front pour confirmer le paiement Stripe

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(length = 3, nullable = false) // ex: "EUR", "USD", "MAD"
    private String currency;

    @Enumerated(EnumType.STRING)
    private StatutPaiement statut;

    @Enumerated(EnumType.STRING)
    private MethodePaiement methode;

    private LocalDateTime datePaiement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    // Assurer que la date est remplie automatiquement
    @PrePersist
    protected void onCreate() {
        this.datePaiement = LocalDateTime.now();
    }
}
