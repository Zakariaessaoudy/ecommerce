package fst.ecommerce.repository;

import fst.ecommerce.entity.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListItemRepository extends JpaRepository<WishListItem,String> {

}
