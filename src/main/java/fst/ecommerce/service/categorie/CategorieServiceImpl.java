package fst.ecommerce.service.categorie;

import fst.ecommerce.entity.Categorie;
import fst.ecommerce.repository.CategorieRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository repository;

    public CategorieServiceImpl(CategorieRepository repository) {
        this.repository = repository;
    }

    @Override
    public Categorie create(Categorie categorie) {
        return repository.save(categorie);
    }

    @Override
    public Categorie update(Long id, Categorie categorie) {
        // TODO: implement update
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Categorie getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Categorie> getAll() {
        return repository.findAll();
    }
}
