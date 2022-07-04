package com.esgi.tags.controller;

import com.esgi.tags.data.Category;
import com.esgi.tags.service.CategoryService;
import com.esgi.tags.service.exception.ResourceConflictException;
import com.esgi.tags.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/categories")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category createCategory(String label) throws ResourceConflictException {
        return categoryService.create(label);
    }

    @GetMapping("/{categoryLabel}")
    public Category getCategoryByLabel(@PathVariable(name = "categoryLabel") String categoryLabel)
            throws ResourceNotFoundException {
        return categoryService.getByLabel(categoryLabel);
    }

    @GetMapping
    public Page<Category> getAllCategoriesPaged(Pageable pageable) {
        return categoryService.getAllPaged(pageable);
    }


    @DeleteMapping("/{categoryLabel}")
    public void deleteCategory(@PathVariable(name = "categoryLabel") String categoryLabel) {
        categoryService.deleteByLabel(categoryLabel);
    }
}