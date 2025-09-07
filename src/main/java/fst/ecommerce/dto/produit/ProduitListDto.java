package fst.ecommerce.dto.produit;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


public class ProduitListDto {
    private String id;

    @NotBlank
    private String nom;

    @Positive
    private double prix;

    private String image;
}
