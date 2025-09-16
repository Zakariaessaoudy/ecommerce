package fst.ecommerce.repository;

import fst.ecommerce.entity.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanierRepository extends JpaRepository<Panier, String> {
}
