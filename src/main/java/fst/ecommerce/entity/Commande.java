package fst.ecommerce.entity;

import fst.ecommerce.enums.StatutCommande;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
