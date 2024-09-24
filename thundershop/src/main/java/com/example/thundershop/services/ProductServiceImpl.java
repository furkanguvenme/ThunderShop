package com.example.thundershop.services;

import com.example.thundershop.entity.Category;
import com.example.thundershop.entity.Product;
import com.example.thundershop.entity.User;
import com.example.thundershop.exceptions.ThunderException;
import com.example.thundershop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    private CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Product saveProduct(long id, Product product) {
        Category category = categoryService.findCategoryById(id);
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(long id) {
            Product deleteProduct = findProductById(id);
            productRepository.delete(deleteProduct);
            return deleteProduct;
    }

    @Override
    public Product updateProduct(long id, Product product) {
        Product product1 = findProductById(id);
        product.setId(product1.getId());
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(long id) {
        if(productRepository.findById(id).isPresent()){
            return productRepository.findById(id).get();
        }
        throw new ThunderException("There is not product which have " + id, HttpStatus.BAD_REQUEST);
    }

    @Override
    public Product findByNameAndSize(String teamName, int size){
        return productRepository.findProductByNameAndSize(teamName, size);
    }
}
