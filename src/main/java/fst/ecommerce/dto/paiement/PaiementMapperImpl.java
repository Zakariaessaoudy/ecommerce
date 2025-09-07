package fst.ecommerce.dto.paiement;


import fst.ecommerce.entity.Commande;
import fst.ecommerce.entity.Paiement;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapperImpl implements PaiementMapper {

    @Override
    public PaiementDto toDTO(Paiement paiement) {
        if (paiement == null) return null;

        PaiementDto dto = new PaiementDto();
        dto.setId(paiement.getId());
        dto.setPaymentId(paiement.getPaymentId());
        dto.setTransactionReference(paiement.getTransactionReference());
        dto.setClientSecret(paiement.getClientSecret());
        dto.setMontant(paiement.getMontant());
        dto.setCurrency(paiement.getCurrency());
        dto.setStatut(paiement.getStatut());
        dto.setMethode(paiement.getMethode());
        dto.setDatePaiement(paiement.getDatePaiement());

        if (paiement.getCommande() != null) {
            dto.setCommandeId(paiement.getCommande().getId());
        }

        return dto;
    }

    @Override
    public Paiement toEntity(PaiementDto dto) {
        if (dto == null) return null;

        Paiement paiement = new Paiement();
        paiement.setId(dto.getId());
        paiement.setPaymentId(dto.getPaymentId());
        paiement.setTransactionReference(dto.getTransactionReference());
        paiement.setClientSecret(dto.getClientSecret());
        paiement.setMontant(dto.getMontant());
        paiement.setCurrency(dto.getCurrency());
        paiement.setStatut(dto.getStatut());
        paiement.setMethode(dto.getMethode());
        paiement.setDatePaiement(dto.getDatePaiement());

        if (dto.getCommandeId() != null) {
            Commande commande = new Commande();
            commande.setId(dto.getCommandeId());
            paiement.setCommande(commande);
        }

        return paiement;
    }
}