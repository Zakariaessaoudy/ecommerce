package fst.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    @OneToMany(mappedBy = "categorie")
    private Collection<Produit> produits;
}
