package fst.ecommerce.repository;

import fst.ecommerce.entity.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishListItemRepository extends JpaRepository<WishListItem,String> {
    List<WishListItem> findByWishList_Id(String wishlistId);
    //Optional<WishListItem> findByIdAndWishlistId(String itemId, String wishlistId);

}
