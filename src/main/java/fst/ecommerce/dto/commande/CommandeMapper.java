package fst.ecommerce.dto.commande;

import fst.ecommerce.dto.ligneCommande.LigneCommandDto;
import fst.ecommerce.dto.ligneCommande.LigneCommandMapper;
import fst.ecommerce.entity.Commande;
import fst.ecommerce.entity.LigneCommande;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LigneCommandMapper.class})
public interface CommandeMapper {
    CommandeDto toDTO(Commande commande);
    Commande toEntity(CommandeDto commandeDto);

}