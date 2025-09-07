package fst.ecommerce.dto.produit;



import fst.ecommerce.dto.avis.AvisMapper;
import fst.ecommerce.dto.categorie.CategorieMapper;
import fst.ecommerce.entity.Produit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CategorieMapper.class, AvisMapper.class})
public interface ProduitMapper {

    ProduitDto toDto(Produit produit);
    Produit toEntity(ProduitDto dto);

    ProduitListDto toListDto(Produit produit);
    ProduitDetailsDto toDetailsDto(Produit produit);
    ProduitAdminDto toAdminDto(Produit produit);
}
