package fst.ecommerce.service.commande;

import fst.ecommerce.dto.commande.CommandeDto;
import fst.ecommerce.dto.commande.CommandeMapper;
import fst.ecommerce.dto.ligneCommande.LigneCommandDto;
import fst.ecommerce.dto.ligneCommande.LigneCommandMapper;
import fst.ecommerce.entity.Commande;
import fst.ecommerce.entity.LigneCommande;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;
    private final LigneCommandMapper ligneCommandMapper ;

    @Override
    public CommandeDto create(CommandeDto commandeDto) {
        Commande commande = commandeMapper.toEntity(commandeDto);
        Commande saved = commandeRepository.save(commande);
        return commandeMapper.toDTO(saved);
    }

    @Override
    public CommandeDto getById(String id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Commande non trouvée avec id : " + id));
        return commandeMapper.toDTO(commande);
    }

    @Override
    public List<CommandeDto> getAll() {
        return commandeRepository.findAll()
                .stream()
                .map(commandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        if (!commandeRepository.existsById(id)) {
            throw new RessourceNotFound("Commande non trouvée avec id : " + id);
        }
        commandeRepository.deleteById(id);
    }
    @Override
    public CommandeDto addLigneToCommande(String commandeId, LigneCommandDto ligneDto) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RessourceNotFound("Commande non trouvée avec id : " + commandeId));

        // ⚠️ Utiliser le mapper de LigneCommande, pas celui de Commande
        LigneCommande ligne = ligneCommandMapper.toEntity(ligneDto);
        ligne.setCommande(commande);

        commande.getLigneCommandes().add(ligne);

        Commande saved = commandeRepository.save(commande);
        return commandeMapper.toDTO(saved);
    }

    @Override
    public CommandeDto removeLigneFromCommande(String commandeId, String ligneId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RessourceNotFound("Commande non trouvée avec id : " + commandeId));

        // ⚠️ Ici orphanRemoval = true fera la suppression en DB
        boolean removed = commande.getLigneCommandes().removeIf(l -> l.getId().equals(ligneId));

        if (!removed) {
            throw new RessourceNotFound("LigneCommande non trouvée avec id : " + ligneId);
        }

        Commande saved = commandeRepository.save(commande);
        return commandeMapper.toDTO(saved);
    }

}
