package fst.ecommerce.service;

import java.util.List;

public interface CrudDtoService<ID, DTO>{
    DTO create(DTO dto);
    DTO update(DTO dto);
    void delete(ID id);
    DTO getById(ID id);
    List<DTO> getAll();
}
