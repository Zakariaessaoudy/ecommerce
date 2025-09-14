package fst.ecommerce.dto.wishlist;

import fst.ecommerce.dto.wishListItem.WishListItemDto;
import lombok.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDto {

    private String id;
    private Date dateCreation;
    private List<WishListItemDto> wishListItems = new ArrayList<>();
}
