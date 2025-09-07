package fst.ecommerce.dto.ligneCommande;

import fst.ecommerce.dto.commande.CommandeDto;
import fst.ecommerce.dto.produit.ProduitDto;


public class LigneCommandDto {
    private String id;
    private int quantite;
    private double prixUnitaire;
    private ProduitDto produit;
    private CommandeDto commande;
}
