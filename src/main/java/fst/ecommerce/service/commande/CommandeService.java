package fst.ecommerce.service.commande;

import fst.ecommerce.dto.commande.CommandeDto;
import fst.ecommerce.dto.ligneCommande.LigneCommandDto;

import java.util.List;

public interface CommandeService {
    CommandeDto create(CommandeDto commandeDto);
    CommandeDto getById(String id);
    List<CommandeDto> getAll();
    void delete(String id);
    CommandeDto addLigneToCommande(String commandeId, LigneCommandDto ligneDto);
    CommandeDto removeLigneFromCommande(String commandeId, String ligneId);
}
