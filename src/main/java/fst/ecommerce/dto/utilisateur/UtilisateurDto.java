package fst.ecommerce.dto.utilisateur;


import fst.ecommerce.enums.Role;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UtilisateurDto {
        private String id;
        private String nom;
        private String prenom;
        private String email;
        private String telephone;
        private String role;
        private String wishListId;
        private String panierId;
        private List<String> commandeIds;
}