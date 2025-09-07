package fst.ecommerce.dto.wishListItem;

import fst.ecommerce.dto.produit.ProduitDto;
import fst.ecommerce.dto.wishlist.WishlistDto;
import fst.ecommerce.entity.Produit;
import fst.ecommerce.entity.WishList;
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
    private WishlistDto wishList;
    private ProduitDto produit;
}
