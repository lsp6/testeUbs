package com.ubs.teste.repository;

import com.ubs.teste.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductOrderByPrice(String product);
}
