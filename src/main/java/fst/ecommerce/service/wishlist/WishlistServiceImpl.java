package fst.ecommerce.service.wishlist;

import fst.ecommerce.entity.Wishlist;
import fst.ecommerce.repository.WishlistRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository repository;

    public WishlistServiceImpl(WishlistRepository repository) {
        this.repository = repository;
    }

    @Override
    public Wishlist create(Wishlist wishlist) {
        return repository.save(wishlist);
    }

    @Override
    public Wishlist update(Long id, Wishlist wishlist) {
        // TODO: implement update
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Wishlist getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Wishlist> getAll() {
        return repository.findAll();
    }
}
