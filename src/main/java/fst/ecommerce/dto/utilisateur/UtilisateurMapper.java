package fst.ecommerce.dto.utilisateur;

import fst.ecommerce.entity.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    // Entity → DTO
    @Mapping(source = "wishlist.id", target = "wishListId")
    @Mapping(source = "panier.id", target = "panierId")
    @Mapping(target = "commandeIds", expression = "java(utilisateur.getCommandes() != null ? utilisateur.getCommandes().stream().map(c -> c.getId()).collect(Collectors.toList()) : null)")
    UtilisateurDto toDTO(Utilisateur utilisateur);

    // DTO → Entity
    @Mapping(source = "wishListId", target = "wishlist.id")
    @Mapping(source = "panierId", target = "panier.id")
    @Mapping(target = "commandes", ignore = true) //  on ignore pour éviter boucle
    Utilisateur toEntity(UtilisateurDto dto);
}
