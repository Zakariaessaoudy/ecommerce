package fst.ecommerce.dto.commande;


import fst.ecommerce.enums.StatutCommande;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommandeDto {
    private Long id;
    private Date dateCommande;
    private StatutCommande statutCommande;
    private Long paiementId;
    private List<Long> produitsIds;
    private Long utilisateurId;
}
