package fst.ecommerce.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    private String nom;

    @NotBlank
    private String description;

    @Positive
    private double prix;

    private String image;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    @JsonIgnore
    private Categorie categorie;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore

    private List<Avis> avis;


    @OneToMany(mappedBy = "produit")
    @JsonIgnore
    private List<LigneCommande> ligneCommandes;

    @OneToMany(mappedBy = "produit")
    @JsonIgnore
    private List<WishListItem> wishListItems;
}
