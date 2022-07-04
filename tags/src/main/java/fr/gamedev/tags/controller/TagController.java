package fr.gamedev.tags.controller;

import fr.gamedev.tags.data.Tag;
import fr.gamedev.tags.service.TagService;
import fr.gamedev.tags.service.exception.ResourceConflictException;
import fr.gamedev.tags.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tags")
@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/category/{categoryLabel}")
    public Tag createTag(@PathVariable(name = "categoryLabel") String categoryLabel, String tagLabel)
            throws ResourceNotFoundException, ResourceConflictException {
        return tagService.create(categoryLabel, tagLabel);
    }

    @GetMapping("/{tagLabel}")
    public Tag getTagByLabel(@PathVariable(name = "tagLabel") String tagLabel) throws ResourceNotFoundException{
        return tagService.getByLabel(tagLabel);
    }

    @GetMapping
    public Page<Tag> getAllTagsPaged(Pageable pageable) {
        return tagService.getAllPaged(pageable);
    }

    @DeleteMapping("/{tagLabel}")
    public void deleteTag(@PathVariable(name = "tagLabel") String tagLabel) {
        tagService.delete(tagLabel);
    }
}
