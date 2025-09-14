package fst.ecommerce.dto.ligneCommande;

import fst.ecommerce.dto.commande.CommandeDto;
import fst.ecommerce.dto.produit.ProduitSimpleDto;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandDto {
    private String id;
    private int quantite;
    private double prixUnitaire;
    private ProduitSimpleDto produit;
    private CommandeDto commande;
}
