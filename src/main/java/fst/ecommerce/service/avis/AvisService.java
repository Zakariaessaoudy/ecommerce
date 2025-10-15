package fst.ecommerce.service.avis;

import fst.ecommerce.dto.avis.AvisDto;
import fst.ecommerce.entity.Avis;
import fst.ecommerce.service.CrudDtoService;

import java.util.List;

public interface AvisService extends CrudDtoService<String, AvisDto> {
    public List<AvisDto> getAllByProduct(String id);
}