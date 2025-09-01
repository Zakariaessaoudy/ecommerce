package fst.ecommerce.service.produit;

import fst.ecommerce.entity.Produit;
import fst.ecommerce.repository.ProduitRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository repository;

    public ProduitServiceImpl(ProduitRepository repository) {
        this.repository = repository;
    }

    @Override
    public Produit create(Produit produit) {
        return repository.save(produit);
    }

    @Override
    public Produit update(Long id, Produit produit) {
        // TODO: implement update
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Produit getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Produit> getAll() {
        return repository.findAll();
    }
}
