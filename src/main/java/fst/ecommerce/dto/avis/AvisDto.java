package fst.ecommerce.dto.avis;

import fst.ecommerce.entity.Produit;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AvisDto {
    private String id;
    private int note;
    private String comment;
    private Produit produit;
}
