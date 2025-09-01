package fst.ecommerce.service.panier;

import fst.ecommerce.entity.Panier;
import fst.ecommerce.repository.PanierRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PanierServiceImpl implements PanierService {

    private final PanierRepository repository;

    public PanierServiceImpl(PanierRepository repository) {
        this.repository = repository;
    }

    @Override
    public Panier create(Panier panier) {
        return repository.save(panier);
    }

    @Override
    public Panier update(Long id, Panier panier) {
        // TODO: implement update
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Panier getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Panier> getAll() {
        return repository.findAll();
    }
}
