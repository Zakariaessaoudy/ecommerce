package fst.ecommerce.dto.utilisateur;

import fst.ecommerce.entity.Utilisateur;

public interface UtilisateurMapper {
    UtilisateurDto toDTO(Utilisateur utilisateur);
    Utilisateur toEntity(UtilisateurDto utilisateurDto);

}
