package org.semennikov.repository;

import org.semennikov.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserId(Long userId);
    Optional<Product> findByPaymentNumber(String paymentNumber);
    boolean existsByPaymentNumber(String paymentNumber);
}
