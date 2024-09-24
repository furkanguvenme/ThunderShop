package com.example.thundershop.repository;

import com.example.thundershop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.teamName = :teamName AND p.size = :size")
    Product findProductByNameAndSize(String teamName, int size);
}
