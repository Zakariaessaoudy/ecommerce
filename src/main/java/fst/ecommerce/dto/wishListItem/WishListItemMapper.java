package fst.ecommerce.dto.wishListItem;


import fst.ecommerce.dto.produit.ProduitMapper;
import fst.ecommerce.dto.wishlist.WishListMapper;
import fst.ecommerce.dto.wishlist.WishlistDto;
import fst.ecommerce.entity.WishList;
import fst.ecommerce.entity.WishListItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProduitMapper.class, WishListMapper.class})
public interface WishListItemMapper {
    WishListItemDto toWishListItemDto(WishList wishList);
    WishListItem toWishListItem(WishListItemDto wishListItemDto);
}
