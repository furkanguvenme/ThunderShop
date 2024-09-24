package com.example.thundershop.services;

import com.example.thundershop.entity.Category;
import com.example.thundershop.entity.Product;
import com.example.thundershop.exceptions.ThunderException;
import com.example.thundershop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findByCategoryId(long id) {
        if(categoryRepository.findById(id).isPresent()){
            return categoryRepository.findById(id).get().getProducts();
        }
        throw new ThunderException("There is not category which have " + id, HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(long id) {
        if(categoryRepository.findById(id).isPresent()){
            return categoryRepository.findById(id).get();
        }
        throw new ThunderException("There is not category which have " + id, HttpStatus.BAD_REQUEST);
    }

}
