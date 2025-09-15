package fst.ecommerce.dto.panier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PanierDto {
    private Long id;
    private Date dateCreation;
    private Long utilisateurId;              // juste l’ID du user
    private List<Long> panierItemIds;        // juste les IDs des items (lightweight DTO)
}
