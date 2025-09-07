package fst.ecommerce.service.wishlist;

import fst.ecommerce.entity.WishList;
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
    public WishList create(WishList wishlist) {
        return repository.save(wishlist);
    }

    @Override
    public WishList update(Long id, WishList wishlist) {
        // TODO: implement update
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public WishList getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<WishList> getAll() {
        return repository.findAll();
    }
}
