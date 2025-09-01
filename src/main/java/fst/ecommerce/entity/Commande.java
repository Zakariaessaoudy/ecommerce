package fst.ecommerce.entity;

import fst.ecommerce.enums.StatutCommande;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Temporal(TemporalType.DATE)
    private Date dateCommande;
    private StatutCommande statutCommande;
    @OneToOne
    private Paiement paiement;
    @ManyToMany
    private Collection<Produit> produits;
    @ManyToOne
    private Utilisateur utilisateur;
}
