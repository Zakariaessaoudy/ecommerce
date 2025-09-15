package fst.ecommerce.dto.panier;

import fst.ecommerce.entity.Panier;

public interface PanierMapper {
    PanierDto toDTO(Panier panier);
    Panier toEntity(PanierDto dto);
}
