package fst.ecommerce.dto.panier;

import fst.ecommerce.dto.panierItem.PanierItemDto;
import fst.ecommerce.dto.utilisateur.UtilisateurDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PanierDto {
    private String id;
    private Date dateCreation;
    private UtilisateurDto utilisateur;
    private List<PanierItemDto> panierItems;

}
