package fst.ecommerce.dto.commande;


import fst.ecommerce.dto.ligneCommande.LigneCommandDto;
import fst.ecommerce.entity.LigneCommande;
import fst.ecommerce.enums.StatutCommande;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDto {
    private String id;
    private Date dateCommande;
    private StatutCommande statutCommande;
    private Long paiementId;
    private List<LigneCommandDto> ligneCommandes;
    private Long utilisateurId;
}
