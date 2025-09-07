package fst.ecommerce.repository;

import fst.ecommerce.entity.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande,String> {
}
