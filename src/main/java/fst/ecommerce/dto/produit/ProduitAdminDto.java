package fst.ecommerce.dto.produit;

import fst.ecommerce.dto.avis.AvisDto;
import fst.ecommerce.dto.ligneCommande.LigneCommandDto;
import fst.ecommerce.entity.Avis;
import fst.ecommerce.entity.Categorie;
import fst.ecommerce.entity.LigneCommande;
import fst.ecommerce.entity.WishListItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class ProduitAdminDto {
    private String id;

    @NotBlank
    private String nom;

    @NotBlank
    private String description;

    @Positive
    private double prix;

    private String image;

    private Categorie categorie;

    private List<AvisDto> avis;

    private List<LigneCommandDto> ligneCommandes;

    private List<WishListItem> wishListItems;
}
