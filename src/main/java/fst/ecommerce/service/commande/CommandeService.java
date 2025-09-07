package fst.ecommerce.service.commande;

import fst.ecommerce.dto.categorie.CategorieDto;
import fst.ecommerce.dto.commande.CommandeDto;
import fst.ecommerce.entity.Categorie;
import fst.ecommerce.entity.Commande;
import fst.ecommerce.service.CrudDtoService;

import java.util.List;

public interface CommandeService    {
    CommandeDto createCommande (CommandeDto commandeDto);

    void deleteCommande (Long id );

    List<CommandeDto> getAllCommande() ;

    CommandeDto getCommandeById( Long id) ;


}
