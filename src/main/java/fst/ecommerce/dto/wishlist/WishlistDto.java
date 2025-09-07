package fst.ecommerce.dto.wishlist;

import fst.ecommerce.dto.wishListItem.WishListItemDto;
import fst.ecommerce.entity.WishListItem;


import java.util.Date;
import java.util.List;

public class WishlistDto {

    private String id;
    private Date dateCreation;
    private List<WishListItemDto> wishListItems ;
}
