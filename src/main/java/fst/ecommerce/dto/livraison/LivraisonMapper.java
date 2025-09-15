package fst.ecommerce.dto.livraison;

import fst.ecommerce.dto.commande.CommandeMapper;
import fst.ecommerce.entity.Commande;
import fst.ecommerce.entity.Livraison;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = {CommandeMapper.class})
public interface LivraisonMapper {
    LivraisonDto toDTO(Livraison livraison);
    Livraison toEntity(LivraisonDto dto);
}
