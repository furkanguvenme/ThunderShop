package com.example.thundershop.services;


import com.example.thundershop.entity.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(long id, Product product);

    Product deleteProduct(long id);

    Product updateProduct(long id, Product product);

    List<Product> findAllProduct();

    Product findProductById(long id);

    Product findByNameAndSize(String teamName, int size);

}
