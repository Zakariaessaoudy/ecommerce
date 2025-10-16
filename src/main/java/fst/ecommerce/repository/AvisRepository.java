package fst.ecommerce.repository;

import fst.ecommerce.entity.Avis;
import fst.ecommerce.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisRepository extends JpaRepository<Avis, String> {

    //    @Query("SELECT * FROM Avis a WHERE produit_id = :produitId")
    //    List<Avis> findByProduitIdCustom(@Param("produitId") String produitId);
    List<Avis> findByProduitId(String Id);
}
