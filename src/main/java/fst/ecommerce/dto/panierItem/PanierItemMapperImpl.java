package fst.ecommerce.dto.panierItem;

import fst.ecommerce.entity.PanierItem;
import fst.ecommerce.entity.Produit;
import org.springframework.stereotype.Component;

@Component
public class PanierItemMapperImpl implements PanierItemMapper {
    @Override
    public PanierItemDto toDTO(PanierItem panierItem) {

        return PanierItemDto.builder()
                .id(panierItem.getId())
                .quantite(panierItem.getQuantite())
                .panier(panierItem.getPanier())
                .produitId(panierItem.getProduit().getId())
                .build();
    }

    @Override
    public PanierItem toEntity(PanierItemDto panierItemDto) {
        Produit produit = new Produit();
        produit.setId(panierItemDto.getId());
        return PanierItem.builder()
                .id(panierItemDto.getId())
                .quantite(panierItemDto.getQuantite())
                .panier(panierItemDto.getPanier())
                .produit(produit)
                .build();
    }
}
