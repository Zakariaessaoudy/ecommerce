package fst.ecommerce.dto.ligneCommande;


import fst.ecommerce.dto.produit.ProduitMapper;
import fst.ecommerce.entity.LigneCommande;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProduitMapper.class})
public interface LigneCommandMapper {
    LigneCommandDto toDTO(LigneCommande ligneCommande);
    LigneCommande toEntity(LigneCommandDto ligneCommandDto);

}
