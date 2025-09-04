package fst.ecommerce.dto.utilisateur;


import fst.ecommerce.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UtilisateurDto {
        private Long id;
        private String nom;
        private String email;
        private String telephone;
        private Role role;
}
