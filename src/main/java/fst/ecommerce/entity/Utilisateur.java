package fst.ecommerce.entity;

import fst.ecommerce.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private  String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String telephone;
    private String password;
    private Role role;

    @OneToOne
    private WishList wishlist;
    @OneToOne
    private Panier panier;
    @OneToMany(mappedBy = "utilisateur")
    private Collection<Commande> commandes;

}
