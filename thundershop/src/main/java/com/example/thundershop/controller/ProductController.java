package com.example.thundershop.controller;

import com.example.thundershop.dto.ProductDto;
import com.example.thundershop.entity.Product;
import com.example.thundershop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> findAll(){
        return productService.findAllProduct().stream().map(product ->
                new ProductDto(product.getId(),
                        product.getTeamName(),
                        product.getSize(),
                        product.getPrice(),
                        product.getStock(),
                        product.getImages())).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable long id){
        Product product = productService.findProductById(id);
        return new ProductDto(product.getId(), product.getTeamName(), product.getSize(), product.getPrice(), product.getStock(),product.getImages());
    }

    @PostMapping("/admin/{id}")
    public ProductDto save(@PathVariable long id, @RequestBody Product product){
        Product product1 = productService.saveProduct(id, product);
        return new ProductDto(product1.getId(), product1.getTeamName(), product1.getSize(), product1.getPrice(), product1.getStock(),product1.getImages());
    }

    @PutMapping("/admin/{id}")
    public ProductDto updateProduct(@PathVariable long id, @RequestBody Product product){
        Product product1 = productService.updateProduct(id, product);
        return new ProductDto(product1.getId(), product1.getTeamName(), product1.getSize(), product1.getPrice(), product1.getStock(),product1.getImages());
    }

    @DeleteMapping("/admin/{id}")
    public ProductDto deleteProduct(@PathVariable long id){
        Product product = productService.deleteProduct(id);
        return new ProductDto(product.getId(), product.getTeamName(), product.getSize(), product.getPrice(), product.getStock(),product.getImages());
    }
}
