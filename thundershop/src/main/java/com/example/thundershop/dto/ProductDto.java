package com.example.thundershop.dto;

import com.example.thundershop.entity.ProductImage;

import java.util.List;

public record ProductDto(long id, String teamName, int size, double price, int stock, List<ProductImage> images) {
}
