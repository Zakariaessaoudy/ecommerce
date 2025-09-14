package fst.ecommerce.dto.wishlist;


import fst.ecommerce.dto.wishListItem.WishListItemMapper;
import fst.ecommerce.entity.WishList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {WishListItemMapper.class})
public interface WishListMapper {
    WishlistDto toDTO(WishList wishList);
    WishList toEntity(WishlistDto wishlistDto);
}
