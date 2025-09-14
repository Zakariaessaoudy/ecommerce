package fst.ecommerce.service.produit;


import fst.ecommerce.dto.produit.ProduitAdminDto;
import fst.ecommerce.dto.produit.ProduitDetailsDto;
import fst.ecommerce.dto.produit.ProduitListDto;
import fst.ecommerce.dto.produit.ProduitSimpleDto;
import fst.ecommerce.service.CrudDtoService;

import java.util.List;


public interface ProduitService extends CrudDtoService<String, ProduitAdminDto> {
    List<ProduitSimpleDto> LandingPageProduits();
    List<ProduitListDto> ListRechercheProduits(String keyword);
    ProduitDetailsDto findById(String id);

}