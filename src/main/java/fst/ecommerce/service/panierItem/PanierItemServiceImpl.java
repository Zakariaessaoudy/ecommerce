package fst.ecommerce.service.panierItem;

import fst.ecommerce.entity.PanierItem;
import fst.ecommerce.repository.PanierItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanierItemServiceImpl implements PanierItemService {

    private final PanierItemRepository repository;

    public PanierItemServiceImpl(PanierItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public PanierItem create(PanierItem item) {
        return repository.save(item);
    }

    @Override
    public PanierItem update(Long id, int quantity) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setQuantity(quantity);
                    return repository.save(existing);
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PanierItem get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<PanierItem> getAll() {
        return repository.findAll();
    }

    @Override
    public double getTotal() {
        return repository.findAll()
                .stream()
                .mapToDouble(item -> item.getProduit().getPrix() * item.getQuantity())
                .sum();
    }
}
