package fst.ecommerce.service.livraison;

import fst.ecommerce.dto.livraison.LivraisonDto;
import fst.ecommerce.enums.StatutLivraison;

import java.util.List;

public interface LivraisonService {
    LivraisonDto create(LivraisonDto dto);
    LivraisonDto getById(String id);
    List<LivraisonDto> getAll();
    LivraisonDto updateStatut(String id, StatutLivraison statut);
    void delete(String id);
}

