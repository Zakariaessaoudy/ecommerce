package fst.ecommerce.dto.livraison;

import fst.ecommerce.dto.livraison.LivraisonDto;
import fst.ecommerce.dto.livraison.LivraisonMapper;
import fst.ecommerce.entity.Commande;
import fst.ecommerce.entity.Livraison;
import org.springframework.stereotype.Component;

@Component
public class LivraisonMapperImpl implements LivraisonMapper {

    @Override
    public LivraisonDto toDTO(Livraison livraison) {
        if (livraison == null) return null;

        LivraisonDto dto = new LivraisonDto();
        dto.setId(livraison.getId());
        dto.setTransporteur(livraison.getTransporteur());
        dto.setDateExpedition(livraison.getDateExpedition());
        dto.setDateLivraison(livraison.getDateLivraison());
        dto.setStatutLivraison(livraison.getStatutLivraison());
        dto.setCommandeId(
                livraison.getCommande() != null ? livraison.getCommande().getId() : null
        );
        return dto;
    }

    @Override
    public Livraison toEntity(LivraisonDto dto) {
        if (dto == null) return null;

        Livraison livraison = new Livraison();
        livraison.setId(dto.getId());
        livraison.setTransporteur(dto.getTransporteur());
        livraison.setDateExpedition(dto.getDateExpedition());
        livraison.setDateLivraison(dto.getDateLivraison());
        livraison.setStatutLivraison(dto.getStatutLivraison());

        if (dto.getCommandeId() != null) {
            Commande commande = new Commande();
            commande.setId(dto.getCommandeId());
            livraison.setCommande(commande);
        }
        return livraison;
    }
}
