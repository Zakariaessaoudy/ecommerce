package fst.ecommerce.dto.categorie;

import fst.ecommerce.entity.Categorie;
import org.springframework.stereotype.Component;

@Component
public class CategorieMapperImpl implements CategorieMapper {
    @Override
    public CategorieDto toDTO(Categorie categorie) {
        return CategorieDto.builder()
                .id(categorie.getId())
                .name(categorie.getName())
                .description(categorie.getDescription())
                .produits(categorie.getProduits())
                .build();

    }

    @Override
    public Categorie toEntity(CategorieDto categorieDto) {

        return Categorie.builder()
                .id(categorieDto.getId())
                .name(categorieDto.getName())
                .description(categorieDto.getDescription())
                .produits(categorieDto.getProduits())
                .build();
    }
}
