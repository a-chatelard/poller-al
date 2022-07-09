package com.esgi.tags.service;

import com.esgi.tags.service.exception.ResourceConflictException;
import com.esgi.tags.data.Category;
import com.esgi.tags.repository.CategoryRepository;
import com.esgi.tags.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    /**
     * The category repository.
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Get a category by label.
     * @param categoryLabel the category label.
     * @return the related category entity.
     * @throws ResourceNotFoundException Category not found.
     */
    public Category getByLabel(final String categoryLabel) throws ResourceNotFoundException {
        var category = categoryRepository.findById(categoryLabel);

        if (category.isEmpty()) {
            throw new ResourceNotFoundException(Category.class, categoryLabel);
        }

        return category.get();
    }

    /**
     * Get all categories with paging.
     * @param pageable the paging parameters.
     * @return the list of category paged.
     */
    public Page<Category> getAllPaged(final Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    /**
     * Create a catetgory.
     * @param categoryLabel the category label.
     * @return the created category entity.
     * @throws ResourceConflictException Category already exists.
     */
    public Category create(final String categoryLabel) throws ResourceConflictException {
        if (categoryRepository.existsById(categoryLabel)) {
            throw new ResourceConflictException(Category.class, categoryLabel);
        }

        var category = new Category(categoryLabel);

        categoryRepository.save(category);

        return category;
    }

    /**
     * Delete a category.
     * @param categoryLabel the category label.
     */
    public void deleteByLabel(final String categoryLabel) {
        categoryRepository.deleteById(categoryLabel);
    }
}
