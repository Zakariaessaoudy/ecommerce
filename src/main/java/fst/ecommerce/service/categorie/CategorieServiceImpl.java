package fst.ecommerce.service.categorie;

import fst.ecommerce.dto.categorie.CategorieDto;
import fst.ecommerce.dto.categorie.CategorieMapper;
import fst.ecommerce.entity.Categorie;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.CategorieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository repository;
    private final CategorieMapper mapper;

    @Override
    public CategorieDto create(CategorieDto categorieDto) {
        log.info("Creating new category: {}", categorieDto);
        Categorie categorie = mapper.toEntity(categorieDto);
        Categorie saved = repository.save(categorie);
        return mapper.toDTO(saved);
    }

    @Override
    public CategorieDto update(CategorieDto categorieDto) {
        log.info("Updating category with id {}", categorieDto.getId());
        repository.findById(categorieDto.getId())
                .orElseThrow(() -> new RessourceNotFound("Category with id " + categorieDto.getId() + " not found"));
        Categorie updated = repository.save(mapper.toEntity(categorieDto));
        return mapper.toDTO(updated);
    }

    @Override
    public void delete(String id) {
        log.info("Deleting category with id {}", id);
        repository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Category with id " + id + " not found"));
        repository.deleteById(id);
    }

    @Override
    public CategorieDto getById(String id) {
        log.info("Fetching category with id {}", id);
        Categorie cat = repository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Category with id " + id + " not found"));
        return mapper.toDTO(cat);
    }

    @Override
    public List<CategorieDto> getAll() {
        log.info("Fetching all categories");
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}
