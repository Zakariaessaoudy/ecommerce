package fst.ecommerce.service.utilisateur;

import fst.ecommerce.entity.Utilisateur;
import fst.ecommerce.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository repository;

    public UtilisateurServiceImpl(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @Override
    public Utilisateur create(Utilisateur utilisateur) {
        return repository.save(utilisateur);
    }

    @Override
    public Utilisateur update(Long id, Utilisateur utilisateur) {
        // TODO: implement update
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Utilisateur getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Utilisateur> getAll() {
        return repository.findAll();
    }
}
