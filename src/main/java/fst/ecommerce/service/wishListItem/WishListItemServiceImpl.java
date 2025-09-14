package fst.ecommerce.service.wishListItem;

import fst.ecommerce.dto.wishListItem.WishListItemDto;
import fst.ecommerce.dto.wishListItem.WishListItemMapper;
import fst.ecommerce.dto.wishlist.WishListMapper;
import fst.ecommerce.entity.WishList;
import fst.ecommerce.entity.WishListItem;
import fst.ecommerce.exception.RessourceNotFound;
import fst.ecommerce.repository.WishListItemRepository;
import fst.ecommerce.repository.WishlistRepository;
import fst.ecommerce.service.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WishListItemServiceImpl implements WishListItemService {

    private final WishListItemRepository repository;
    private final WishListItemMapper mapper1;
    private final WishListMapper mapper2;
    private final WishlistService wishlistService;
    private final WishlistRepository wishlistRepository;
    private final WishListItemRepository wishListItemRepository;

     @Override
    public WishListItemDto create(WishListItemDto dto) {
         /*
    *
        log.info("Creating new WishListItem: {}", dto);
        WishListItem entity = mapper1.toEntity(dto);
        WishListItem saved = repository.save(entity);
        return mapper1.toDTO(saved);
        */
     return null;
    }


    @Override
    public WishListItemDto addToWishlist(String wishlistId, WishListItemDto itemDto) {
        WishList wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found with id " + wishlistId));
        itemDto.setWishlistId(wishlist.getId());
        WishListItem entity = mapper1.toEntity(itemDto);
        WishListItem saved = wishListItemRepository.save(entity);

        return mapper1.toDTO(saved);
    }

    @Override
    public WishListItemDto update(WishListItemDto dto) {
        log.info("Updating WishListItem with id {}", dto.getId());
        repository.findById(dto.getId())
                .orElseThrow(() -> new RessourceNotFound("WishListItem with id " + dto.getId() + " not found"));
        WishListItem updated = repository.save(mapper1.toEntity(dto));
        return mapper1.toDTO(updated);
    }

    @Override
    public void delete(String id) {
        log.info("Deleting WishListItem with id {}", id);
        WishListItem entity = repository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("WishListItem with id " + id + " not found"));
        repository.delete(entity);
    }

    public void deleteFromWishList(String wishListId,String wishlistItemId){
        WishList wishList = mapper2.toEntity(wishlistService.getById(wishListId));
        WishListItem entity = repository.findById(wishlistItemId)
                .orElseThrow(() -> new RessourceNotFound("WishListItem with id " + wishlistItemId + " not found"));
        wishList.getWishListItems().remove(entity);
        wishlistService.update(mapper2.toDTO(wishList));
        repository.delete(entity);
    }

    @Override
    public List<WishListItemDto> getAllByWishlistId(String wishlistId) {
         return wishListItemRepository.findByWishList_Id(wishlistId)
                 .stream()
                 .map(mapper1::toDTO)
                 .collect(Collectors.toList());
    }


    @Override
    public WishListItemDto getById(String id) {
        log.info("Fetching WishListItem with id {}", id);
        WishListItem entity = repository.findById(id)
                .orElseThrow(() -> new RessourceNotFound("WishListItem with id " + id + " not found"));
        return mapper1.toDTO(entity);
    }

    @Override
    public List<WishListItemDto> getAll() {
        return List.of();
    }


}
