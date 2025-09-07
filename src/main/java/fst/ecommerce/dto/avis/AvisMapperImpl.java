package fst.ecommerce.dto.avis;

import fst.ecommerce.entity.Avis;

public class AvisMapperImpl implements AvisMapper {
    @Override
    public AvisDto toDTO(Avis avis) {
        return AvisDto.builder()
                .id(avis.getId())
                .note(avis.getNote())
                .comment(avis.getComment())
                .produit(avis.getProduit())
                .build();
    }

    @Override
    public Avis toEntity(AvisDto avisDto) {

        return Avis.builder()
                .id(avisDto.getId())
                .note(avisDto.getNote())
                .comment(avisDto.getComment())
                .produit(avisDto.getProduit())
                .build();
    }
}
