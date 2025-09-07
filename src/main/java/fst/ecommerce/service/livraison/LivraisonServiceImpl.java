package fst.ecommerce.service.livraison;

import fst.ecommerce.dto.livraison.LivraisonDto;
import fst.ecommerce.dto.livraison.LivraisonDto;
import fst.ecommerce.entity.Commande;
import fst.ecommerce.entity.Livraison;
import fst.ecommerce.enums.StatutLivraison;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.dto.livraison.LivraisonMapper;
import fst.ecommerce.repository.CommandeRepository;
import fst.ecommerce.repository.LivraisonRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivraisonServiceImpl {

    private final LivraisonRepository livraisonRepository;
    private final CommandeRepository commandeRepository;
    private final LivraisonMapper livraisonMapper;



    public LivraisonServiceImpl(LivraisonRepository livraisonRepository, CommandeRepository commandeRepository, LivraisonMapper livraisonMapper) {
        this.livraisonRepository = livraisonRepository;
        this.commandeRepository = commandeRepository;
        this.livraisonMapper = livraisonMapper;
    }

    // 🔹 Créer une livraison pour une commande
    public LivraisonDto createLivraison(Long commandeId, String transporteur) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        Livraison livraison = new Livraison();
        livraison.setTransporteur(transporteur);
        livraison.setDateExpedition(new Date());
        livraison.setDateLivraison(new Date(System.currentTimeMillis() + (3L * 24 * 60 * 60 * 1000))); // +3 jours
        livraison.setStatutLivraison(StatutLivraison.valueOf("EN_PREPARATION"));
        livraison.setCommande(commande);

        Livraison saved = livraisonRepository.save(livraison);
        return livraisonMapper.toDTO(saved);
    }

    // 🔹 Mettre à jour le statut
    public LivraisonDto updateStatut(Long id, String statut) {
        Livraison livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Livraison d'id"+id +"non trouvée"));

        livraison.setStatutLivraison(StatutLivraison.valueOf(statut));
        Livraison updated = livraisonRepository.save(livraison);
        return livraisonMapper.toDTO(updated);
    }

    // 🔹 Récupérer toutes les livraisons
    public List<LivraisonDto> getAllLivraisons() {
        return livraisonRepository.findAll()
                .stream().map(livraisonMapper::toDTO)
                .collect(Collectors.toList());
    }
}
