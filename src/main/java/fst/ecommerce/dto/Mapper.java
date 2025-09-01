package fst.ecommerce.dto;

public interface Mapper<ENTITY, DTO> {
    DTO toDTO(ENTITY entity);
    ENTITY toEntity(DTO dto);
}
