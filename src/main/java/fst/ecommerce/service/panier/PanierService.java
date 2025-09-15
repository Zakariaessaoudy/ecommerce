package fst.ecommerce.service.panier;

import fst.ecommerce.dto.categorie.CategorieDto;
import fst.ecommerce.dto.panier.PanierDto;
import fst.ecommerce.entity.Categorie;
import fst.ecommerce.entity.Panier;
import fst.ecommerce.service.CrudDtoService;

import java.util.List;

public interface PanierService extends CrudDtoService<Panier, PanierDto> {
    Panier create(Panier panier) ;
    Panier update(Long id, Panier panier);
    void delete(Long id);
    Panier getById(Long id);
    List<PanierDto> getAll();
}