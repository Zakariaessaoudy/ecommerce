package fst.ecommerce.dto.commande;

import fst.ecommerce.dto.ligneCommande.LigneCommandMapper;
import fst.ecommerce.entity.Commande;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring", uses = {LigneCommandMapper.class})
public interface CommandeMapper {
    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    @Mapping(source = "paiement.id", target = "paiementId")
    CommandeDto toDTO(Commande entity);
    @Mapping(source = "utilisateurId", target = "utilisateur.id")
    @Mapping(source = "paiementId", target = "paiement.id")
    Commande toEntity(CommandeDto dto);
}


