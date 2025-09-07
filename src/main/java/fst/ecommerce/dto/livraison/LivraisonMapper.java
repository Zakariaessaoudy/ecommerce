package fst.ecommerce.dto.livraison;

import fst.ecommerce.entity.Livraison;

public interface LivraisonMapper {
    LivraisonDto toDTO(Livraison livraison);
    Livraison toEntity(LivraisonDto dto);
}
