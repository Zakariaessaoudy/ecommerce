package fst.ecommerce.entity;

import fst.ecommerce.enums.StatutLivraison;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String transporteur;

    @Temporal(TemporalType.DATE)
    private Date dateExpedition;

    @Temporal(TemporalType.DATE)
    private Date dateLivraison;

    private StatutLivraison statutLivraison; // EN_PREPARATION, EN_EXPEDITION, EN_COURS, LIVREE

    @OneToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;
}
