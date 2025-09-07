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
    @OneToMany(mappedBy = "commande",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<LigneCommande> ligneCommandes;
    @ManyToOne
    private Utilisateur utilisateur;
}
