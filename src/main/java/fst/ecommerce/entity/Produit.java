package fst.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nom;
    private String description;
    private double prix;
    private String image;

    @ManyToOne
    private Categorie categorie;
    @OneToMany(mappedBy = "produit")
    private Collection<Avis> avis;
    @ManyToMany
    @JoinTable(name = "LigneCommande")
    private Collection<Commande> commandes;
    @ManyToMany
    @JoinTable(name = "wishListItem")
    private Collection<Wishlist> wishlists;

}
