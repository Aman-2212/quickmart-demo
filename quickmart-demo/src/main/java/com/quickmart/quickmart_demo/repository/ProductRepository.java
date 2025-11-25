package com.quickmart.quickmart_demo.repository;

import com.quickmart.quickmart_demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
