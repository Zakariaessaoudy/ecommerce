package fst.ecommerce.service.wishListItem;

import fst.ecommerce.dto.wishListItem.WishListItemDto;
import fst.ecommerce.service.CrudDtoService;

import java.util.List;

public interface WishListItemService extends CrudDtoService<String, WishListItemDto> {
    WishListItemDto addToWishlist(String wishlistId, WishListItemDto itemDto);
    void deleteFromWishList(String WishListId,String WishlistItemId);
    List<WishListItemDto> getAllByWishlistId(String wishlistId);
}

