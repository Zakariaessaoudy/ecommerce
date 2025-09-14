package fst.ecommerce.repository;

import fst.ecommerce.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, String> {
    @Query("SELECT p FROM Produit p WHERE LOWER(p.nom) LIKE LOWER(CONCAT('%', :kw, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :kw, '%'))")
    List<Produit> searchProduits(@Param("kw") String kw);
}
