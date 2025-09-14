package fst.ecommerce.dto.wishListItem;


import fst.ecommerce.dto.produit.ProduitMapper;
import fst.ecommerce.dto.wishlist.WishListMapper;
import fst.ecommerce.entity.WishListItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProduitMapper.class, WishListMapper.class})
public interface WishListItemMapper {
    WishListItemDto toDTO(WishListItem wishListItem);
    WishListItem toEntity(WishListItemDto wishListItemDto);
}
