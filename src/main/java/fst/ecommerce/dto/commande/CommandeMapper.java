package fst.ecommerce.dto.commande;

import fst.ecommerce.entity.Commande;

public interface CommandeMapper {
    CommandeDto toDTO (Commande commande);
    Commande toEntity (CommandeDto commandeDto);
}
