package fst.ecommerce.entity;

import fst.ecommerce.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name = "utilisateurs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String nom;
    private String prenom;

    @Column(unique = true , nullable = false)
    private String email;
    private String telephone;
    private String password;
    private Role role;

    @OneToOne
    private Wishlist wishlist;
    @OneToOne
    private Panier panier;
    @OneToMany(mappedBy = "utilisateur")
    private Collection<Commande> commandes;

}
