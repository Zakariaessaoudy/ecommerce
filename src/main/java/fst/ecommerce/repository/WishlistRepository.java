package fst.ecommerce.repository;

import fst.ecommerce.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<WishList, Long> {
}
