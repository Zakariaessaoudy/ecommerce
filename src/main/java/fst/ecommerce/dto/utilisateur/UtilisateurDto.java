package fst.ecommerce.dto.utilisateur;


import fst.ecommerce.enums.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UtilisateurDto {
        private String id;
        private String nom;
        private String email;
        private String telephone;
        private Role role;
}
