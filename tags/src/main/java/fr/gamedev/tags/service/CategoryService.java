package fr.gamedev.tags.service;

import fr.gamedev.tags.data.Category;
import fr.gamedev.tags.repository.CategoryRepository;
import fr.gamedev.tags.service.exception.ResourceConflictException;
import fr.gamedev.tags.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getByLabel(String categoryLabel) throws ResourceNotFoundException {
        var category = categoryRepository.findById(categoryLabel);

        if (category.isEmpty()) {
            throw new ResourceNotFoundException(Category.class, categoryLabel);
        }

        return category.get();
    }

    public Page<Category> getAllPaged(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category create(String categoryLabel) throws ResourceConflictException {
        if (categoryRepository.existsById(categoryLabel)) {
            throw new ResourceConflictException(Category.class, categoryLabel);
        }

        var category = new Category(categoryLabel);

        categoryRepository.save(category);

        return category;
    }

    public void deleteByLabel(String categoryLabel) {
        categoryRepository.deleteById(categoryLabel);
    }
}
