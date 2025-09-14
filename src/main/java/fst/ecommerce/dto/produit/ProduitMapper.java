package fst.ecommerce.dto.produit;



import fst.ecommerce.dto.avis.AvisMapper;
import fst.ecommerce.dto.categorie.CategorieMapper;
import fst.ecommerce.entity.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {CategorieMapper.class, AvisMapper.class})
public interface ProduitMapper {

    ProduitAdminDto toDto(Produit produit);
    @Mappings({})
    Produit toEntity(ProduitAdminDto dto);

    ProduitListDto toListDto(Produit produit);
    ProduitDetailsDto toDetailsDto(Produit produit);
    ProduitSimpleDto toSimpleDto(Produit produit);
}
