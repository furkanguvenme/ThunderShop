package com.example.thundershop.repository;

import com.example.thundershop.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ProductImage, Long> {
}
