package fst.ecommerce.dto.avis;

import fst.ecommerce.entity.Produit;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvisDto {
    private String id;
    private int note;
    private String comment;
    private Produit produit;
}
