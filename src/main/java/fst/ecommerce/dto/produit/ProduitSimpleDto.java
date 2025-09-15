package fst.ecommerce.dto.produit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProduitSimpleDto {
    private String id;

    @NotBlank
    private String nom;

    @NotBlank
    private String description;

    @Positive
    private double prix;

    private String image;


}
