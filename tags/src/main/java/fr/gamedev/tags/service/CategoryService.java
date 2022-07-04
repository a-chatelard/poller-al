package fr.gamedev.tags.service;

import fr.gamedev.tags.data.Category;
import fr.gamedev.tags.repository.CategoryRepository;
import fr.gamedev.tags.service.exception.ResourceConflictException;
import fr.gamedev.tags.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getById(Long categoryId) throws ResourceNotFoundException {
        var category = categoryRepository.findById(categoryId);

        if (category.isEmpty()) {
            throw new ResourceNotFoundException(Category.class, categoryId);
        }

        return category.get();
    }

    public Page<Category> getAllPaged(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category create(String categoryLabel) throws ResourceConflictException {
        var existingCategory = categoryRepository.findByLabel(categoryLabel);

        if (existingCategory.isPresent()) {
            throw new ResourceConflictException("A category of label " + categoryLabel + " already exists.");
        }

        var category = new Category(categoryLabel);

        categoryRepository.save(category);

        return category;
    }
}
