package fst.ecommerce.service.panier;

import fst.ecommerce.dto.panier.PanierDto;
import fst.ecommerce.dto.panier.PanierMapper;
import fst.ecommerce.dto.panierItem.PanierItemDto;
import fst.ecommerce.dto.panierItem.PanierItemMapper;
import fst.ecommerce.entity.Panier;
import fst.ecommerce.entity.PanierItem;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.PanierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;
    private final PanierMapper panierMapper;
    private final PanierItemMapper panierItemMapper ;

    public PanierServiceImpl(PanierRepository panierRepository, PanierMapper panierMapper, PanierItemMapper panierItemMapper) {
        this.panierRepository = panierRepository;
        this.panierMapper = panierMapper;
        this.panierItemMapper = panierItemMapper;
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

    @Override
    public PanierDto addPanierItemToPanier(String panierId , PanierItemDto panierItemDto){
        Panier panier =panierRepository.findById(panierId)
                .orElseThrow(() -> new RessourceNotFound("Panier non trouvé avec id : " + panierId));

        PanierItem panierItem = panierItemMapper.toEntity(panierItemDto);
        panierItem.setPanier(panier);

        panier.getPanierItem().add(panierItem);
        Panier saved = panierRepository.save(panier);
        return panierMapper.toDTO(saved);
    }

    @Override
    public  PanierDto removePanierItemFromPanier( String panierId , String panierItemId){
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(()-> new RessourceNotFound("Panìer non trouvé avec id  :" +panierId));

        // ⚠️ Ici orphanRemoval = true fera la suppression en DB
        boolean removed = panier.getPanierItem().removeIf(panierItem -> panierItem.getId().equals(panierItemId));

        if (!removed) {
            throw new RessourceNotFound("PanierItem non trouvé avec id : " + panierItemId);
        }

        Panier saved = panierRepository.save(panier);
        return panierMapper.toDTO(saved);
    }
}
