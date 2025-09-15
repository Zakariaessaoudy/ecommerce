package fst.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String code;

    private double reduction; // % ou montant fixe

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private boolean active = true; // activé/désactivé

    private int usageLimit; // limite d'utilisation
    private int usedCount = 0; // compteur
}
