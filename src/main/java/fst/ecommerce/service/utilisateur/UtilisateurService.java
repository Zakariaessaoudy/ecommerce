package fst.ecommerce.service.utilisateur;

import fst.ecommerce.dto.categorie.CategorieDto;
import fst.ecommerce.dto.utilisateur.UtilisateurDto;
import fst.ecommerce.entity.Categorie;
import fst.ecommerce.entity.Utilisateur;
import fst.ecommerce.service.CrudDtoService;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto create(UtilisateurDto utilisateurDto);

    UtilisateurDto getById(String id);

    List<UtilisateurDto> getAll();

    void delete(String id);

}