package fst.ecommerce.service.panier;

import fst.ecommerce.dto.panier.PanierDto;
import fst.ecommerce.dto.panier.PanierMapper;
import fst.ecommerce.entity.Panier;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.PanierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;
    private final PanierMapper panierMapper;

    public PanierServiceImpl(PanierRepository panierRepository, PanierMapper panierMapper) {
        this.panierRepository = panierRepository;
        this.panierMapper = panierMapper;
    }

    @Override
    public PanierDto create(PanierDto dto) {
        Panier panier = panierMapper.toEntity(dto);
        Panier saved = panierRepository.save(panier);
        return panierMapper.toDTO(saved);
    }

    @Override
    public PanierDto getById(String id) {
        Panier panier = panierRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Panier non trouvé"));
        return panierMapper.toDTO(panier);
    }

    @Override
    public List<PanierDto> getAll() {
        return panierRepository.findAll()
                .stream()
                .map(panierMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        panierRepository.deleteById(id);
    }
}
