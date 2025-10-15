/*
package fst.ecommerce.dto.avis;

import fst.ecommerce.entity.Avis;
import fst.ecommerce.entity.Produit;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Component
public class AvisMapperImpl2 implements AvisMapper {
    @Override
    public AvisDto toDTO(Avis avis) {
        return AvisDto.builder()
                .id(avis.getId())
                .note(avis.getNote())
                .comment(avis.getComment())
                .produitId(avis.getProduit().getId())
                .build();
    }

    @Override
    public Avis toEntity(AvisDto avisDto) {
        Produit produit = new Produit();
        produit.setId(avisDto.getProduitId());
        return Avis.builder()
                .id(avisDto.getId())
                .note(avisDto.getNote())
                .comment(avisDto.getComment())
                .produit(produit)
                .build();
    }
}
*/
