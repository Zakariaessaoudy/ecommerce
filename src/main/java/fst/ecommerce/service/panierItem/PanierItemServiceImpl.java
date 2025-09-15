package fst.ecommerce.service.panierItem;

import fst.ecommerce.dto.panierItem.PanierItemDto;
import fst.ecommerce.dto.panierItem.PanierItemMapper;
import fst.ecommerce.entity.PanierItem;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.PanierItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PanierItemServiceImpl implements PanierItemService {

    private final PanierItemRepository repository;
    private final PanierItemMapper mapper;

    @Override
    public PanierItemDto create(PanierItemDto dto) {
        log.info("Creating new PanierItem: {}", dto);
        PanierItem entity = mapper.toEntity(dto);
        PanierItem saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public PanierItemDto update(PanierItemDto dto) {
        log.info("Updating PanierItem with id {}", dto.getId());
        repository.findById(dto.getId())
                .orElseThrow(() -> new RessourceNotFound("PanierItem with id " + dto.getId() + " not found"));
        PanierItem updated = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(updated);
    }

    @Override
    public void delete(String id) {
        log.info("Deleting PanierItem with id {}", id);
        PanierItem entity = repository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("PanierItem with id " + id + " not found"));
        repository.delete(entity);
    }

    @Override
    public PanierItemDto getById(String id) {
        log.info("Fetching PanierItem with id {}", id);
        PanierItem entity = repository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("PanierItem with id " + id + " not found"));
        return mapper.toDTO(entity);
    }

    @Override
    public List<PanierItemDto> getAll() {
        log.info("Fetching all PanierItems");
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}
