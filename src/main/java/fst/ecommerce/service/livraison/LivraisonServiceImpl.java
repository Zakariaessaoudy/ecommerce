package fst.ecommerce.service.livraison;

import fst.ecommerce.entity.Livraison;
import fst.ecommerce.repository.LivraisonRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LivraisonServiceImpl implements LivraisonService {

    private final LivraisonRepository repository;

    public LivraisonServiceImpl(LivraisonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Livraison create(Livraison livraison) {
        return repository.save(livraison);
    }

    @Override
    public Livraison update(Long id, Livraison livraison) {
        // TODO: implement update
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Livraison getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Livraison> getAll() {
        return repository.findAll();
    }
}
