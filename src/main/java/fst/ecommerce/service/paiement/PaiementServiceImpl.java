package fst.ecommerce.service.paiement;

import fst.ecommerce.entity.Paiement;
import fst.ecommerce.repository.PaiementRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaiementServiceImpl implements PaiementService {

    private final PaiementRepository repository;

    public PaiementServiceImpl(PaiementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Paiement create(Paiement paiement) {
        return repository.save(paiement);
    }

    @Override
    public Paiement update(Long id, Paiement paiement) {
        // TODO: implement update
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Paiement getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Paiement> getAll() {
        return repository.findAll();
    }
}
