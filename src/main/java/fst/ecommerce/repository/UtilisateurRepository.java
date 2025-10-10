package fst.ecommerce.repository;

import fst.ecommerce.entity.Utilisateur;
import fst.ecommerce.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
    Optional<Utilisateur> findByEmail(String username);
    boolean existsByEmail(String email);
    List<Utilisateur> findByRole(Role role);
}
