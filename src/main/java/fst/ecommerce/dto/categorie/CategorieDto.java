package fst.ecommerce.dto.categorie;

import fst.ecommerce.entity.Produit;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorieDto {
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    private Collection<Produit> produits;
}
