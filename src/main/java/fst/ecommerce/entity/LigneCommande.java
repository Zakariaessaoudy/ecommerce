package fst.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int quantite;
    private double prixUnitaire;

    @ManyToOne
    private Produit produit;
    @ManyToOne
    private Commande commande;
}
