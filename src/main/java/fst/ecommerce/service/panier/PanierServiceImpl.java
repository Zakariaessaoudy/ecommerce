package fst.ecommerce.service.panier;

import fst.ecommerce.dto.panier.PanierDto;
import fst.ecommerce.entity.Panier;
import fst.ecommerce.repository.PanierRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PanierServiceImpl implements PanierService {

    private final PanierRepository repository;
    private final PanierMapper panierMapper ;

    public PanierServiceImpl(PanierRepository repository , P) {
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
    public List<PanierDto> getAll() {
        return repository.findAll() .stream()
                .map(panierMapper::toDTO)
                .collect(Collectors.toList());
    }
}
