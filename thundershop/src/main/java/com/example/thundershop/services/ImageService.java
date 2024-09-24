package com.example.thundershop.services;

import com.example.thundershop.entity.ProductImage;

import java.util.List;

public interface ImageService {

    ProductImage findImageById(long imageId);

    ProductImage save(long productId, ProductImage image);

    List<ProductImage> findAllImages(long productId);

    ProductImage deleteImageById(long imageId);

}
