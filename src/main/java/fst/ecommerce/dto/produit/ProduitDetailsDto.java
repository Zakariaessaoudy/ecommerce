package fst.ecommerce.dto.produit;

import fst.ecommerce.dto.avis.AvisDto;
import fst.ecommerce.dto.ligneCommande.LigneCommandDto;
import lombok.*;


import java.util.List;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProduitDetailsDto {
    private String id;
    private String nom;
    private String description;
    private double prix;
    private String image;
    private List<AvisDto> avis;
    private List<LigneCommandDto> ligneCommandes;
}
