package fst.ecommerce.dto.utilisateur;

import fst.ecommerce.entity.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class utilisateurMapperImpl  implements UtilisateurMapper{

    @Override
    public UtilisateurDto toDTO(Utilisateur utilisateur) {
        if (utilisateur == null) return null;

        UtilisateurDto dto = new UtilisateurDto();

        dto.setId(utilisateur.getId());
        dto.setNom(utilisateur.getNom());
        dto.setEmail(utilisateur.getEmail());
        dto.setTelephone(utilisateur.getTelephone());
        dto.setRole(utilisateur.getRole());
        return dto;

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



