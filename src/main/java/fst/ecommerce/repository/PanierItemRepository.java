package fst.ecommerce.repository;

import fst.ecommerce.entity.PanierItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanierItemRepository extends JpaRepository<PanierItem, Long> {
}
