package fst.ecommerce.dto.livraison;

import fst.ecommerce.enums.StatutLivraison;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonDto {
    private String id;
    private String transporteur;
    private Date dateExpedition;
    private Date dateLivraison;
    private StatutLivraison statutLivraison;
    private String commandeId;
}
