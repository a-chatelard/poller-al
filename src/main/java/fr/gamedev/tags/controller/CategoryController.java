package fr.gamedev.tags.controller;

import fr.gamedev.tags.data.Category;
import fr.gamedev.tags.service.CategoryService;
import fr.gamedev.tags.service.exception.ResourceConflictException;
import fr.gamedev.tags.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable(name = "categoryId") Long categoryId) throws ResourceNotFoundException {
        return categoryService.getById(categoryId);
    }

    @GetMapping
    public Page<Category> getAllCategoriesPaged(Pageable pageable) {
        return categoryService.getAllPaged(pageable);
    }

    @PostMapping
    public Category createCategory(String label) throws ResourceConflictException {
        return categoryService.create(label);
    }
}
