package fst.ecommerce.service.avis;

import fst.ecommerce.entity.Avis;
import fst.ecommerce.repository.AvisRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AvisServiceImpl implements AvisService {

    private final AvisRepository repository;

    public AvisServiceImpl(AvisRepository repository) {
        this.repository = repository;
    }

    @Override
    public Avis create(Avis avis) {
        return repository.save(avis);
    }

    @Override
    public Avis update(Long id, Avis avis) {
        // TODO: implement update
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Avis getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Avis> getAll() {
        return repository.findAll();
    }
}
