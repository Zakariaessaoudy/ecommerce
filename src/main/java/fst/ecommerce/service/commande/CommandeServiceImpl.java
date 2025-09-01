package fst.ecommerce.service.commande;

import fst.ecommerce.entity.Commande;
import fst.ecommerce.repository.CommandeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository repository;

    public CommandeServiceImpl(CommandeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Commande create(Commande commande) {
        return repository.save(commande);
    }

    @Override
    public Commande update(Long id, Commande commande) {
        // TODO: implement update
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Commande getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Commande> getAll() {
        return repository.findAll();
    }
}
