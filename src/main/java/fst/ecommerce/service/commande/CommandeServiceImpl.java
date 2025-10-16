package fst.ecommerce.service.commande;

import fst.ecommerce.dto.categorie.CategorieDto;
import fst.ecommerce.dto.commande.CommandeDto;
import fst.ecommerce.dto.commande.CommandeMapper;
import fst.ecommerce.dto.ligneCommande.LigneCommandDto;
import fst.ecommerce.dto.ligneCommande.LigneCommandMapper;
import fst.ecommerce.entity.Categorie;
import fst.ecommerce.entity.Commande;
import fst.ecommerce.entity.LigneCommande;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j


public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;
    private final LigneCommandMapper ligneCommandMapper ;

    @Override
    public CommandeDto create(CommandeDto commandeDto) {
        log.info("Creating new commande: {}", commandeDto);
        commandeDto.setId(null);
        Commande commande = commandeMapper.toEntity(commandeDto);
        Commande saved = commandeRepository.save(commande);
        return commandeMapper.toDTO(saved);
    }
    @Override
    public CommandeDto update(CommandeDto commandeDto) {
        log.info("Updating category with id {}", commandeDto.getId());
        commandeRepository.findById(commandeDto.getId())
                .orElseThrow(() -> new RessourceNotFound("commande with id " + commandeDto.getId() + " not found"));
        Commande updated = commandeRepository.save(commandeMapper.toEntity(commandeDto));
        return commandeMapper.toDTO(updated);
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
