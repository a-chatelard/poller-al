package com.esgi.tags.service;

import com.esgi.tags.data.QuestionTag;
import com.esgi.tags.data.UserTag;
import com.esgi.tags.repository.QuestionTagRepository;
import com.esgi.tags.repository.UserTagRepository;
import com.esgi.tags.service.exception.ResourceConflictException;
import com.esgi.tags.data.Tag;
import com.esgi.tags.repository.TagRepository;
import com.esgi.tags.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TagService {

    /**
     * The category service.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * The user tag repository.
     */
    @Autowired
    private UserTagRepository userTagRepository;

    /**
     * The question tag repository.
     */
    @Autowired
    private QuestionTagRepository questionTagRepository;

    /**
     * The tag repository.
     */
    @Autowired
    private TagRepository tagRepository;

    /**
     * Create a tag.
     * @param categoryLabel the related category label.
     * @param tagLabel the tag label.
     * @return the created tag.
     * @throws ResourceNotFoundException Tag or Category not found.
     * @throws ResourceConflictException Tag already exists.
     */
    public Tag create(final String categoryLabel, final String tagLabel)
            throws ResourceNotFoundException, ResourceConflictException {

        if (tagRepository.existsById(tagLabel)) {
            throw new ResourceConflictException(Tag.class, tagLabel);
        }

        var category = categoryService.getByLabel(categoryLabel);

        var tag = new Tag(tagLabel);
        tag.setCategory(category);

        tagRepository.save(tag);

        return tag;
    }

    /**
     * Get a tag by label.
     * @param tagLabel the tag label.
     * @return the related tag.
     * @throws ResourceNotFoundException Tag not found.
     */
    public Tag getByLabel(final String tagLabel) throws ResourceNotFoundException {
        var tag = tagRepository.findById(tagLabel);
        if (tag.isEmpty()) {
            throw new ResourceNotFoundException(Tag.class, tagLabel);
        }
        return tag.get();
    }

    /**
     * Get all the tags with paging.
     * @param pageable the paging parameters.
     * @return the list of tags paged.
     */
    public Page<Tag> getAllPaged(final Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    /**
     * Delete a tag by label.
     * @param tagLabel the tag label.
     */
    public void delete(final String tagLabel) {
        tagRepository.deleteById(tagLabel);
    }

    /**
     * Add a tag to a user.
     * @param tagLabel the tag label.
     * @param userId the user ID.
     * @throws ResourceNotFoundException Tag or User not found.
     */
    public void addUserTag(final String tagLabel, final Long userId) throws ResourceNotFoundException {
        var doesUserExist = doesUserExist(userId);
        if (!doesUserExist) {
            throw new ResourceNotFoundException("User", userId);
        }

        var tag = getByLabel(tagLabel);

        tag.addUserTag(new UserTag(tag, userId));

        tagRepository.save(tag);
    }

    /**
     * Remove a tag from a user.
     * @param tagLabel the tag label.
     * @param userId the user ID.
     * @throws ResourceNotFoundException Tag or User not found.
     */
    public void removeUserTag(final String tagLabel, final Long userId) throws ResourceNotFoundException {
        var doesUserExist = doesUserExist(userId);
        if (!doesUserExist) {
            throw new ResourceNotFoundException("User", userId);
        }

        var tag = getByLabel(tagLabel);

        tag.removeUserTag(new UserTag(tag, userId));

        tagRepository.save(tag);
    }

    /**
     * Add a tag to a label.
     * @param tagLabel the tag label.
     * @param questionId the question ID.
     * @throws ResourceNotFoundException Question or Tag not found.
     */
    public void addQuestionTag(final String tagLabel, final Long questionId) throws ResourceNotFoundException {
        // Devrait être remplacé par une message kafka sur un topic type "QuestionCheckTopic"
        // en envoyant l'identifiant de la question.

        var doesQuestionExist = doesQuestionExist(questionId);

        if (doesQuestionExist) {
            throw new ResourceNotFoundException("Question", questionId);
        }

        var tag = getByLabel(tagLabel);

        tag.addQuestionTag(new QuestionTag(tag, questionId));

        tagRepository.save(tag);
    }

    /**
     * Remove a tag from a question.
     * @param tagLabel the tag label.
     * @param questionId the question ID.
     * @throws ResourceNotFoundException Tag or Question not found.
     */
    public void removeQuestionTag(final String tagLabel, final Long questionId) throws ResourceNotFoundException {
        var doesQuestionExist = doesQuestionExist(questionId);
        if (!doesQuestionExist) {
            throw new ResourceNotFoundException("Question", questionId);
        }

        var tag = getByLabel(tagLabel);

        tag.removeQuestionTag(new QuestionTag(tag, questionId));

        tagRepository.save(tag);
    }

    /**
     * Delete all tags related to a user.
     * @param userId the user ID.
     * @return the amount of related tags deleted.
     */
    public long deleteAllUserTags(final Long userId) {
        return userTagRepository.deleteByUserId(userId);
    }

    /**
     * Delete all tags related to question.
     * @param questionId the question ID.
     * @return the amount of related tags deleted.
     */
    public long deleteAllQuestionTags(final Long questionId) {
        return questionTagRepository.deleteByQuestionId(questionId);
    }

    /**
     * Return if a user exists in the Identity microservice.
     * @param userId the user ID.
     * @return true if the user exists otherwise false.
     */
    private boolean doesUserExist(final Long userId) {
        // Devrait être remplacé par une message kafka sur un topic type "UserCheckTopic"
        // en envoyant l'identifiant de l'utilisateur.

        final String uri = "http://localhost:8080/users/" + userId + "/exists";

        RestTemplate restTemplate = new RestTemplate();
        return Boolean.TRUE.equals(restTemplate.getForObject(uri, Boolean.class));
    }

    /**
     * Return if a question exists in the Questions microservice.
     * @param questionId the question ID.
     * @return true if the question exists otherwise false.
     */
    private boolean doesQuestionExist(final Long questionId) {
        final String uri = "http://localhost:8082/questions/" + questionId + "/exists";

        RestTemplate restTemplate = new RestTemplate();
        return Boolean.TRUE.equals(restTemplate.getForObject(uri, Boolean.class));
    }
}
