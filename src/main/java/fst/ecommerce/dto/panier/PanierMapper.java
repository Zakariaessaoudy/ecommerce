package fst.ecommerce.dto.panier;

import fst.ecommerce.dto.panierItem.PanierItemMapper;
import fst.ecommerce.dto.utilisateur.UtilisateurMapper;
import fst.ecommerce.entity.Panier;
import org.mapstruct.Mapper;


@Mapper( componentModel = "spring" , uses = {UtilisateurMapper.class , PanierItemMapper.class})
public interface PanierMapper {
    PanierDto toDTO(Panier panier);
    Panier toEntity(PanierDto dto);
}
