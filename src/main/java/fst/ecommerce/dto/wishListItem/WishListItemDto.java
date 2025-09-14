package fst.ecommerce.dto.wishListItem;

import fst.ecommerce.dto.produit.ProduitSimpleDto;
import fst.ecommerce.dto.wishlist.WishlistDto;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishListItemDto {
    private String id;
    private Date dateAjout;
    private int priorite;
    private String wishlistId;
    private ProduitSimpleDto produit;
}
