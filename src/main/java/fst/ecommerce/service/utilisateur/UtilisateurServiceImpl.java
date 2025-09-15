package fst.ecommerce.service.utilisateur;

import fst.ecommerce.dto.utilisateur.UtilisateurDto;
import fst.ecommerce.dto.utilisateur.UtilisateurMapper;
import fst.ecommerce.entity.Utilisateur;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
                                  UtilisateurMapper utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }

    @Override
    public UtilisateurDto create(UtilisateurDto utilisateurDTO) {
        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDTO);
        Utilisateur saved = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toDTO(saved);
    }

    @Override
    public UtilisateurDto getById(String id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Utilisateur non trouvé"));
        return utilisateurMapper.toDTO(utilisateur);
    }

    @Override
    public List<UtilisateurDto> getAll() {
        return utilisateurRepository.findAll()
                .stream()
                .map(utilisateurMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        utilisateurRepository.deleteById(id);
    }

}
