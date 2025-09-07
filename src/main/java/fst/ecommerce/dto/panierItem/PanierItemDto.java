package fst.ecommerce.dto.panierItem;

import fst.ecommerce.entity.Panier;
import fst.ecommerce.entity.Produit;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PanierItemDto {

    private String id;
    private int quantite;
    private Panier panier;
    private Produit produit;
}
