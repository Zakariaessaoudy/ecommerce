package fst.ecommerce.dto.commande;

import fst.ecommerce.entity.Commande;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommandeMapper {

    @Mapping(source = "paiement.id", target = "paiementId")
    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    CommandeDto toDTO(Commande commande);

    @Mapping(source = "paiementId", target = "paiement.id")
    @Mapping(source = "utilisateurId", target = "utilisateur.id")
    Commande toEntity(CommandeDto dto);
}
