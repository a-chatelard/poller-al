package com.esgi.tags.controller;

import com.esgi.tags.data.Category;
import com.esgi.tags.service.CategoryService;
import com.esgi.tags.service.exception.ResourceConflictException;
import com.esgi.tags.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/categories")
@RestController
public class CategoryController {

    /**
     * The category service.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Create a category.
     * @param categoryLabel the category's label.
     * @return the created category entity.
     * @throws ResourceConflictException Category already exists.
     */
    @PostMapping
    public Category createCategory(final String categoryLabel) throws ResourceConflictException {
        return categoryService.create(categoryLabel);
    }

    /**
     * Get a category by label.
     * @param categoryLabel the categoryLabel.
     * @return the related category.
     * @throws ResourceNotFoundException Category not found.
     */
    @GetMapping("/{categoryLabel}")
    public Category getCategoryByLabel(final @PathVariable(name = "categoryLabel") String categoryLabel)
            throws ResourceNotFoundException {
        return categoryService.getByLabel(categoryLabel);
    }

    /**
     * Get all the categories with paging.
     * @param pageable the paging parameters.
     * @return the list of categories paged.
     */
    @GetMapping
    public Page<Category> getAllCategoriesPaged(final Pageable pageable) {
        return categoryService.getAllPaged(pageable);
    }

    /**
     * Delete a category.
     * @param categoryLabel the label of the category to delete.
     */
    @DeleteMapping("/{categoryLabel}")
    public void deleteCategory(final @PathVariable(name = "categoryLabel") String categoryLabel) {
        categoryService.deleteByLabel(categoryLabel);
    }
}
