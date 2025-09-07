package fst.ecommerce.dto.commande;

import fst.ecommerce.entity.Commande;
import fst.ecommerce.entity.Paiement;
import fst.ecommerce.entity.Produit;
import fst.ecommerce.entity.Utilisateur;

import java.util.stream.Collectors;

public class CommandeMapperImpl implements CommandeMapper {

    @Override
    public CommandeDto toDTO (Commande commande){
        if(commande == null ) return null;

        CommandeDto dto = new CommandeDto();

        dto.setId(commande.getId());
        dto.setDateCommande(commande.getDateCommande());
        dto.setStatutCommande(commande.getStatutCommande());
        dto.setPaiementId(commande.getPaiement() != null ? commande.getPaiement().getId() : null);
        dto.setUtilisateurId(commande.getUtilisateur() != null ? commande.getUtilisateur().getId() : null);
        dto.setProduitsIds(commande.getProduits()
                .stream()
                .map(Produit::getId)
                .collect(Collectors.toList()));
        return dto ;

    }

    @Override
    public Commande toEntity(CommandeDto commandeDto) {
        if(commandeDto == null) return null ;

        Commande commande = new Commande();

        commande.setId(commandeDto.getId());
        commande.setDateCommande(commandeDto.getDateCommande());
        commande.setStatutCommande(commandeDto.getStatutCommande());
        // ⚠️ Paiement, Utilisateur, Produits seront liés dans le Service
        if (commandeDto.getPaiementId() != null) {
            Paiement paiement = new Paiement();
            paiement.setId(commandeDto.getPaiementId());
            commande.setPaiement(paiement);
        }

        if (commandeDto.getUtilisateurId() != null) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(commandeDto.getUtilisateurId());
            commande.setUtilisateur(utilisateur);
        }

        if (commandeDto.getProduitsIds() != null) {
            commande.setProduits(commandeDto.getProduitsIds()
                    .stream()
                    .map(id -> {
                        Produit p = new Produit();
                        p.setId(id);
                        return p;
                    })
                    .collect(Collectors.toList()));
        }
        return commande;
    }
}
