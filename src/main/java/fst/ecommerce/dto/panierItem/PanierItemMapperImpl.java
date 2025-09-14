package fst.ecommerce.dto.panierItem;

import fst.ecommerce.entity.PanierItem;
import org.springframework.stereotype.Component;

@Component
public class PanierItemMapperImpl implements PanierItemMapper {
    @Override
    public PanierItemDto toDTO(PanierItem panierItem) {
        return PanierItemDto.builder()
                .id(panierItem.getId())
                .quantite(panierItem.getQuantite())
                .panier(panierItem.getPanier())
                .produit(panierItem.getProduit())
                .build();
    }

    @Override
    public PanierItem toEntity(PanierItemDto panierItemDto) {
        return PanierItem.builder()
                .id(panierItemDto.getId())
                .quantite(panierItemDto.getQuantite())
                .panier(panierItemDto.getPanier())
                .produit(panierItemDto.getProduit())
                .build();
    }
}
