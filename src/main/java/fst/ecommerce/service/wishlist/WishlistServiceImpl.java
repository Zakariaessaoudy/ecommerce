package fst.ecommerce.service.wishlist;

import fst.ecommerce.dto.wishlist.WishListMapper;
import fst.ecommerce.dto.wishlist.WishlistDto;
import fst.ecommerce.entity.WishList;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository repository;
    private final WishListMapper mapper;

    @Override
    public WishlistDto create(WishlistDto wishlistDto) {
        log.info("Creating new wishlist: {}", wishlistDto);
        WishList wishList = mapper.toEntity(wishlistDto);
        WishList saved = repository.save(wishList);
        return mapper.toDTO(saved);
    }

    @Override
    public WishlistDto update(WishlistDto wishlistDto) {
        log.info("Updating wishlist with id {}", wishlistDto.getId());
        repository.findById(wishlistDto.getId())
                .orElseThrow(() -> new RessourceNotFound("Wishlist with id " + wishlistDto.getId() + " not found"));
        WishList updated = repository.save(mapper.toEntity(wishlistDto));
        return mapper.toDTO(updated);
    }

    @Override
    public void delete(String id) {
        log.info("Deleting wishlist with id {}", id);
        WishList wishList = repository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Wishlist with id " + id + " not found"));
        repository.delete(wishList);
    }

    @Override
    public WishlistDto getById(String id) {
        log.info("Fetching wishlist with id {}", id);
        WishList wl = repository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Wishlist with id " + id + " not found"));
        return mapper.toDTO(wl);
    }

    @Override
    public List<WishlistDto> getAll() {
        log.info("Fetching all wishlists");
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}
