package fst.ecommerce.service.livraison;

import fst.ecommerce.dto.livraison.LivraisonDto;
import fst.ecommerce.dto.livraison.LivraisonMapper;
import fst.ecommerce.entity.Livraison;
import fst.ecommerce.enums.StatutLivraison;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.LivraisonRepository;
import fst.ecommerce.service.livraison.LivraisonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivraisonServiceImpl implements LivraisonService {

    private final LivraisonRepository livraisonRepository;
    private final LivraisonMapper livraisonMapper;

    public LivraisonServiceImpl(LivraisonRepository livraisonRepository, LivraisonMapper livraisonMapper) {
        this.livraisonRepository = livraisonRepository;
        this.livraisonMapper = livraisonMapper;
    }

    @Override
    public LivraisonDto create(LivraisonDto dto) {
        Livraison livraison = livraisonMapper.toEntity(dto);
        Livraison saved = livraisonRepository.save(livraison);
        return livraisonMapper.toDTO(saved);
    }

    @Override
    public LivraisonDto getById(String id) {
        Livraison livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Livraison non trouvée avec id " + id));
        return livraisonMapper.toDTO(livraison);
    }

    @Override
    public List<LivraisonDto> getAll() {
        return livraisonRepository.findAll()
                .stream()
                .map(livraisonMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LivraisonDto updateStatut(String id, StatutLivraison statut) {
        Livraison livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Livraison non trouvée avec id " + id));
        livraison.setStatutLivraison(statut);
        Livraison updated = livraisonRepository.save(livraison);
        return livraisonMapper.toDTO(updated);
    }

    @Override
    public void delete(String id) {
        if (!livraisonRepository.existsById(id)) {
            throw new RessourceNotFound("Livraison non trouvée avec id " + id);
        }
        livraisonRepository.deleteById(id);
    }
}
