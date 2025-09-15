package fst.ecommerce.dto.panier;

import fst.ecommerce.entity.Panier;
import fst.ecommerce.entity.PanierItem;
import fst.ecommerce.entity.Utilisateur;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PanierMapperImpl implements PanierMapper {

    @Override
    public PanierDto toDTO(Panier panier) {
        if (panier == null) return null;

        return new PanierDto(
                panier.getId(),
                panier.getDateCreation(),
                panier.getUtilisateur() != null ? panier.getUtilisateur().getId() : null,
                panier.getPanierItem() != null ?
                        panier.getPanierItem().stream().map(PanierItem::getId).collect(Collectors.toList())
                        : null
        );
    }

    @Override
    public Panier toEntity(PanierDto dto) {
        if (dto == null) return null;

        Panier panier = new Panier();
        panier.setId(dto.getId());
        panier.setDateCreation(dto.getDateCreation());

        if (dto.getUtilisateurId() != null) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(dto.getUtilisateurId());
            panier.setUtilisateur(utilisateur);
        }

        return panier;
    }
}
