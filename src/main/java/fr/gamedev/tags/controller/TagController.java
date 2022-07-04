package fr.gamedev.tags.controller;

import fr.gamedev.tags.controller.model.TagDTO;
import fr.gamedev.tags.data.Category;
import fr.gamedev.tags.data.Tag;
import fr.gamedev.tags.service.TagService;
import fr.gamedev.tags.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tags")
@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping
    public Tag createTag(@RequestBody TagDTO tag) throws ResourceNotFoundException {
        return tagService.create(tag);
    }

    @DeleteMapping("/{tagId}")
    public void deleteTag(@PathVariable(name = "tagId") Long tagId) {
        tagService.delete(tagId);
    }

    @GetMapping
    public Page<Tag> getAllTagsPaged(Pageable pageable) {
        return tagService.getAllPaged(pageable);
    }

    @GetMapping("/{tagId}")
    public Tag getTagById(@PathVariable(name = "tagId") Long tagId) throws ResourceNotFoundException{
        return tagService.getById(tagId);
    }

    @GetMapping("/user/{userId}")
    public List<Tag> getUserTags(@PathVariable(name = "userId") Long userId) {
        return tagService.getByUserIdPaged(userId);
    }

    @PostMapping("/{tagId}/user/{userId}")
    public Tag addUserTag(@PathVariable(name = "tagId") Long tagId, @PathVariable(name = "userId") Long userId) throws ResourceNotFoundException {
        return tagService.addUserTag(tagId, userId);
    }

    @DeleteMapping("/{tagId}/user/{userId}")
    public void removeUserTag(@PathVariable(name = "tagId") Long tagId, @PathVariable(name = "userId") Long userId) throws ResourceNotFoundException {
        tagService.removeUserTag(tagId, userId);
    }
}
