package fst.ecommerce.dto.produit;

import fst.ecommerce.dto.avis.AvisDto;
import fst.ecommerce.dto.ligneCommande.LigneCommandDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class ProduitDetailsDto {
    private String id;
    private String nom;
    private String description;
    private double prix;
    private String image;
    private List<AvisDto> avis;
    private List<LigneCommandDto> ligneCommandes;
}
