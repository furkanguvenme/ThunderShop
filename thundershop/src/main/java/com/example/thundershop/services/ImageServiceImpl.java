package com.example.thundershop.services;

import com.example.thundershop.entity.Product;
import com.example.thundershop.entity.ProductImage;
import com.example.thundershop.exceptions.ThunderException;
import com.example.thundershop.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{

    private ImageRepository imageRepository;
    private ProductService productService;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ProductService productService) {
        this.imageRepository = imageRepository;
        this.productService = productService;
    }

    @Override
    public ProductImage findImageById(long imageId) {
        return imageRepository.findById(imageId).orElseThrow(() -> new ThunderException("No image found with this id", HttpStatus.BAD_REQUEST));
    }

    @Override
    public ProductImage save(long productId, ProductImage image) {
        Product product = productService.findProductById(productId);
        product.addImage(image);
        productService.saveProduct(product.getCategory().getId(), product);
        return imageRepository.save(image);
    }

    @Override
    public List<ProductImage> findAllImages(long productId) {
        return imageRepository.findAll();
    }

    @Override
    public ProductImage deleteImageById(long imageId) {
        ProductImage image = findImageById(imageId);
        imageRepository.delete(image);
        return image;
    }
}
