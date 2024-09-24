package com.example.thundershop.services;

import com.example.thundershop.entity.Category;
import com.example.thundershop.entity.Product;

import java.util.List;

public interface CategoryService {

    List<Product> findByCategoryId(long id);

    List<Category> getAllCategories();

    Category findCategoryById(long id);

}
