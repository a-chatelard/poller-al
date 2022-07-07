package com.esgi.tags.controller;

import com.esgi.tags.data.Tag;
import com.esgi.tags.service.TagService;
import com.esgi.tags.service.exception.ResourceConflictException;
import com.esgi.tags.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tags")
@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * Create a tag
     * @param categoryLabel the related category label.
     * @param tagLabel the tag label.
     * @return the created tag.
     * @throws ResourceNotFoundException Category not found.
     * @throws ResourceConflictException Tag aleady exists.
     */
    @PostMapping("/category/{categoryLabel}")
    public Tag createTag(@PathVariable(name = "categoryLabel") String categoryLabel, String tagLabel)
            throws ResourceNotFoundException, ResourceConflictException {
        return tagService.create(categoryLabel, tagLabel);
    }

    /**
     * Get a tag.
     * @param tagLabel the tag label.
     * @return the tag entity.
     * @throws ResourceNotFoundException Tag not found.
     */
    @GetMapping("/{tagLabel}")
    public Tag getTagByLabel(@PathVariable(name = "tagLabel") String tagLabel) throws ResourceNotFoundException{
        return tagService.getByLabel(tagLabel);
    }

    /**
     * Get all the tags with paging.
     * @param pageable the pagination.
     * @return the list of tag paged.
     */
    @GetMapping
    public Page<Tag> getAllTagsPaged(Pageable pageable) {
        return tagService.getAllPaged(pageable);
    }

    /**
     * Delete a tag
     * @param tagLabel the tag label.
     */
    @DeleteMapping("/{tagLabel}")
    public void deleteTag(@PathVariable(name = "tagLabel") String tagLabel) {
        tagService.delete(tagLabel);
    }

    /**
     * Add a tag to a user.
     * @param tagLabel the tag label.
     * @param userId the user ID.
     * @throws ResourceNotFoundException Tag or User not found.
     */
    @PostMapping("/{tagLabel}/user/{userId}")
    public void addUserTag(
            @PathVariable(name = "tagLabel") String tagLabel,
            @PathVariable(name = "userId") Long userId)
            throws ResourceNotFoundException {
        tagService.addUserTag(tagLabel, userId);
    }

    /**
     * Remove a tag from a user.
     * @param tagLabel the tag label.
     * @param userId the user ID.
     * @throws ResourceNotFoundException Tag or User not found.
     */
    @DeleteMapping("/{tagLabel}/user/{userId}")
    public void removeUserTag(
    @PathVariable(name = "tagLabel") String tagLabel,
    @PathVariable(name = "userId") Long userId)
            throws ResourceNotFoundException {
        tagService.removeUserTag(tagLabel, userId);
    }

    /**
     * Add a tag to a question.
     * @param tagLabel the tag label.
     * @param questionId the question ID.
     * @throws ResourceNotFoundException Tag or Question not found.
     */
    @PostMapping("/{tagLabel}/question/{questionId}")
    public void addQuestionTag(
            @PathVariable(name = "tagLabel") String tagLabel,
            @PathVariable(name = "questionId") Long questionId)
            throws ResourceNotFoundException {
        tagService.addQuestionTag(tagLabel, questionId);
    }

    /**
     * Remove a tag from a question.
     * @param tagLabel the tag label.
     * @param questionId the question ID.
     * @throws ResourceNotFoundException Tag or Question not found.
     */
    @DeleteMapping("/{tagLabel}/question/{questionId}")
    public void removeQuestionTag(
            @PathVariable(name = "tagLabel") String tagLabel,
            @PathVariable(name = "questionId") Long questionId)
            throws ResourceNotFoundException {
        tagService.removeQuestionTag(tagLabel, questionId);
    }

    /**
     * Remove all the tags related to a user.
     * @param userId the user ID.
     * @return the amount of deleted related tags.
     */
    @DeleteMapping("/user/{userId}")
    public long deleteAllUserTags(@PathVariable(name = "userId") Long userId) {
        return tagService.deleteAllUserTags(userId);
    }

    /**
     * Remove all the tags related to a question.
     * @param questionId the question ID.
     * @return the amount of deleted related tags.
     */
    @DeleteMapping("/question/{questionId}")
    public long deleteAllQuestionTags(@PathVariable(name = "questionId") Long questionId) {
        return tagService.deleteAllQuestionTags(questionId);
    }
}
