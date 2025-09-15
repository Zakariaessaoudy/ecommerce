package fst.ecommerce.service.commande;

import fst.ecommerce.dto.commande.CommandeDto;
import java.util.List;

public interface CommandeService {
    CommandeDto create(CommandeDto commandeDto);
    CommandeDto getById(String id);
    List<CommandeDto> getAll();
    void delete(String id);
}
