package fst.ecommerce.dto.livraison;

import fst.ecommerce.enums.StatutLivraison;
import lombok.Data;

import java.util.Date;

@Data
public class LivraisonDto {
    private Long id;
    private String transporteur;
    private Date dateExpedition;
    private Date dateLivraison;
    private StatutLivraison statutLivraison;
    private Long commandeId;
}
