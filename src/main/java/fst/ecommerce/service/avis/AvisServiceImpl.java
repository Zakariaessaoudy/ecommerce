package fst.ecommerce.service.avis;

import fst.ecommerce.dto.avis.AvisDto;
import fst.ecommerce.dto.avis.AvisMapper;
import fst.ecommerce.entity.Avis;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.AvisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AvisServiceImpl implements AvisService {

    private final AvisRepository repository;
    private final AvisMapper avisMapper;


    @Override
    public AvisDto create(AvisDto avisDto) {
        log.info("Creating new Avis: {}", avisDto);
        Avis avis = avisMapper.toEntity(avisDto);
        Avis saved = repository.save(avis);
        return avisMapper.toDTO(saved);
    }

    @Override
    public AvisDto update(AvisDto avisDto) {
        log.info("Updating Avis with id {}", avisDto.getId());
        repository.findById(avisDto.getId())
                .orElseThrow(() -> new RessourceNotFound("Avis with id " + avisDto.getId() + " not found"));
        Avis updated = repository.save(avisMapper.toEntity(avisDto));
        return avisMapper.toDTO(updated);
    }

    @Override
    public void delete(String id) {
        log.info("Deleting Avis with id {}", id);
        Avis avis = repository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Avis with id " + id + " not found"));
        repository.delete(avis);
    }

    @Override
    public AvisDto getById(String id) {
        log.info("Fetching Avis with id {}", id);
        Avis avis = repository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Avis with id " + id + " not found"));
        return avisMapper.toDTO(avis);
    }

    @Override
    public List<AvisDto> getAll() {
        log.info("Fetching all Avis");
        return repository.findAll()
                .stream()
                .map(avisMapper::toDTO)
                .toList();
    }
}
