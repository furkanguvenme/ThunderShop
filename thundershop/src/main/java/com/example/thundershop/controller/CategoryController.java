package com.example.thundershop.controller;

import com.example.thundershop.dto.CategoryDto;
import com.example.thundershop.dto.ProductDto;
import com.example.thundershop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> findAll(){
        return categoryService.getAllCategories().stream().map(product ->
                new CategoryDto(product.getId(), product.getCategoryType())).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public List<ProductDto> findProductByCategoryId(@PathVariable long id){
        return categoryService.findByCategoryId(id).stream().map(product ->
                new ProductDto(product.getId(),
                        product.getTeamName(),
                        product.getSize(),
                        product.getPrice(),
                        product.getStock(),
                        product.getImages())).collect(Collectors.toList());
    }

}
