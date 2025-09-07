package fst.ecommerce.repository;

import fst.ecommerce.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    Optional<Paiement> findByPaymentId(String paymentId);
    Optional<Paiement> findByTransactionReference(String transactionReference);
}
