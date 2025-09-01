package fst.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "categorie")
    private Collection<Produit> produits;
}
