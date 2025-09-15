package fst.ecommerce.service.panier;

import fst.ecommerce.dto.categorie.CategorieDto;
import fst.ecommerce.dto.panier.PanierDto;
import fst.ecommerce.entity.Categorie;
import fst.ecommerce.entity.Panier;
import fst.ecommerce.service.CrudDtoService;

import java.util.List;

    public interface PanierService {
        PanierDto create(PanierDto dto);
        PanierDto getById(String id);
        List<PanierDto> getAll();
        void delete(String id);
}