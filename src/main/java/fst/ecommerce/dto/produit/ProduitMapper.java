package fst.ecommerce.dto.produit;




import fst.ecommerce.dto.avis.AvisMapper;
import fst.ecommerce.dto.categorie.CategorieMapper;
import fst.ecommerce.entity.Categorie;
import fst.ecommerce.entity.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring",uses = {AvisMapper.class},nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface ProduitMapper {

    // Entity -> DTO
    @Mapping(source = "categorie.id", target = "categorieID")
    ProduitAdminDto toDto(Produit produit);

    // DTO -> Entity
    @Mapping(source = "categorieID", target = "categorie")
    Produit toEntity(ProduitAdminDto dto);

    // Helper method for category conversion
    default Categorie map(String id) {
        if (id == null) return null;
        Categorie categorie = new Categorie();
        categorie.setId(id);
        return categorie;
    }

    ProduitListDto toListDto(Produit produit);
    @Mapping(source = "categorie.id", target = "categorieId")
    ProduitDetailsDto toDetailsDto(Produit produit);

    ProduitSimpleDto toSimpleDto(Produit produit);
}
