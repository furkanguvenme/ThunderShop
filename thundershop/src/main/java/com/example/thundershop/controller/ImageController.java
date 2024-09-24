package com.example.thundershop.controller;

import com.example.thundershop.entity.ProductImage;
import com.example.thundershop.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/image")
public class ImageController {

    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{imageId}")
    public ProductImage findImageById(@PathVariable long imageId){
        return imageService.findImageById(imageId);
    }

    @GetMapping("/user/{productId}")
    public List<ProductImage> findAllImages(@PathVariable long productId){
        return imageService.findAllImages(productId);
    }

    @PostMapping("/{productId}")
    public ProductImage saveImage(@PathVariable long productId, @RequestBody ProductImage image){
        return imageService.save(productId, image);
    }

    @DeleteMapping("/{imageId}")
    public ProductImage deleteImage(@PathVariable long imageId){
        return imageService.deleteImageById(imageId);
    }
}
