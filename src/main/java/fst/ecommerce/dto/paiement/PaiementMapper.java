package fst.ecommerce.dto.paiement;

import fst.ecommerce.entity.Paiement;

public interface PaiementMapper {
    PaiementDto toDTO(Paiement paiement);
    Paiement toEntity(PaiementDto dto);}
