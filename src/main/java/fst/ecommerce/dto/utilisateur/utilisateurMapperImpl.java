package fst.ecommerce.dto.utilisateur;

import fst.ecommerce.entity.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class utilisateurMapperImpl  implements UtilisateurMapper{

    @Override
    public UtilisateurDto toDTO(Utilisateur utilisateur) {
        if (utilisateur == null) return null;

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .email(utilisateur.getEmail())
                .role(utilisateur.getRole())
                .build();

    }


        @Override
        public Utilisateur toEntity( UtilisateurDto dto){
            if(dto == null ) return null;

            Utilisateur utilisateur = new Utilisateur();

            utilisateur.setId(dto.getId());
            utilisateur.setNom(dto.getNom());
            utilisateur.setRole(dto.getRole());
            utilisateur.setEmail(dto.getEmail());
            utilisateur.setTelephone(dto.getTelephone());

            return utilisateur ;
        }

    }



