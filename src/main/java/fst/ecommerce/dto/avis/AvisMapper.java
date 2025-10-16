package fst.ecommerce.dto.avis;

import fst.ecommerce.dto.Mapper;
import fst.ecommerce.entity.Avis;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;




@org.mapstruct.Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface AvisMapper extends Mapper<Avis, AvisDto> {
    // Entity -> DTO
    @Mapping(source = "produit.id", target = "produitId")
    AvisDto toDTO(Avis avis);

    // DTO -> Entity
    @InheritInverseConfiguration
    Avis toEntity(AvisDto dto);
}
