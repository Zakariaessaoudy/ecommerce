package fst.ecommerce.service.commande;

import fst.ecommerce.dto.commande.CommandeDto;
import fst.ecommerce.dto.commande.CommandeMapper;
import fst.ecommerce.entity.Commande;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository repository;
    private final CommandeMapper mapper;

    @Override
    public CommandeDto create(CommandeDto commandeDto) {
        log.info("Creating new commande: {}", commandeDto);
        Commande commande = mapper.toEntity(commandeDto);
        Commande saved = repository.save(commande);
        return mapper.toDTO(saved);
    }

    @Override
    public CommandeDto update(CommandeDto commandeDto) {
        log.info("Updating commande with id {}", commandeDto.getId());
        repository.findById(commandeDto.getId())
                .orElseThrow(() -> new RessourceNotFound("Commande with id " + commandeDto.getId() + " not found"));
        Commande updated = repository.save(mapper.toEntity(commandeDto));
        return mapper.toDTO(updated);
    }

    @Override
    public void delete(String id) {
        log.info("Deleting commande with id {}", id);
        Commande commande = repository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Commande with id " + id + " not found"));
        repository.delete(commande);
    }

    @Override
    public CommandeDto getById(String id) {
        log.info("Fetching commande with id {}", id);
        Commande commande = repository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Commande with id " + id + " not found"));
        return mapper.toDTO(commande);
    }

    @Override
    public List<CommandeDto> getAll() {
        log.info("Fetching all commandes");
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}
