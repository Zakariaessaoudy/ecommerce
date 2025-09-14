package fst.ecommerce.service.LigneCommande;

import fst.ecommerce.dto.ligneCommande.LigneCommandDto;
import fst.ecommerce.dto.ligneCommande.LigneCommandMapper;
import fst.ecommerce.entity.LigneCommande;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.LigneCommandeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LigneCommandeServiceImpl implements LigneCommandeService {

    private final LigneCommandeRepository repository;
    private final LigneCommandMapper mapper;

    @Override
    public LigneCommandDto create(LigneCommandDto dto) {
        log.info("Creating new LigneCommande: {}", dto);
        LigneCommande entity = mapper.toEntity(dto);
        LigneCommande saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public LigneCommandDto update(LigneCommandDto dto) {
        log.info("Updating LigneCommande with id {}", dto.getId());
        repository.findById(dto.getId())
                .orElseThrow(() -> new RessourceNotFound("LigneCommande with id " + dto.getId() + " not found"));
        LigneCommande updated = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(updated);
    }

    @Override
    public void delete(String id) {
        log.info("Deleting LigneCommande with id {}", id);
        LigneCommande entity = repository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("LigneCommande with id " + id + " not found"));
        repository.delete(entity);
    }

    @Override
    public LigneCommandDto getById(String id) {
        log.info("Fetching LigneCommande with id {}", id);
        LigneCommande entity = repository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("LigneCommande with id " + id + " not found"));
        return mapper.toDTO(entity);
    }

    @Override
    public List<LigneCommandDto> getAll() {
        log.info("Fetching all LigneCommandes");
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}
