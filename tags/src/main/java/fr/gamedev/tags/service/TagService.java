package fr.gamedev.tags.service;

import fr.gamedev.tags.data.Tag;
import fr.gamedev.tags.repository.TagRepository;
import fr.gamedev.tags.service.exception.ResourceConflictException;
import fr.gamedev.tags.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagRepository tagRepository;

    public Tag create(String categoryLabel, String tagLabel) throws ResourceNotFoundException, ResourceConflictException {

        if (tagRepository.existsById(tagLabel)) {
            throw new ResourceConflictException(Tag.class, tagLabel);
        }

        var category = categoryService.getByLabel(categoryLabel);

        var tag = new Tag(tagLabel);
        tag.setCategory(category);

        tagRepository.save(tag);

        return tag;
    }

    public Tag getByLabel(String tagLabel) throws ResourceNotFoundException {
        var tag = tagRepository.findById(tagLabel);
        if (tag.isEmpty()) {
            throw new ResourceNotFoundException(Tag.class, tagLabel);
        }
        return tag.get();
    }

    public Page<Tag> getAllPaged(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    public void delete(String tagLabel) {
        tagRepository.deleteById(tagLabel);
    }
}
