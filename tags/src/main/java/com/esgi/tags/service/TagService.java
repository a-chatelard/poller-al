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

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserTagRepository userTagRepository;

    @Autowired
    private QuestionTagRepository questionTagRepository;

    @Autowired
    private TagRepository tagRepository;

    /**
     * Create a tag.
     * @param categoryLabel
     * @param tagLabel
     * @return
     * @throws ResourceNotFoundException
     * @throws ResourceConflictException
     */
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

    public void addUserTag(String tagLabel, Long userId) throws ResourceNotFoundException {
        var doesUserExist = doesUserExist(userId);
        if (!doesUserExist) {
            throw new ResourceNotFoundException("User", userId);
        }

        var tag = getByLabel(tagLabel);

        tag.addUserTag(new UserTag(tag, userId));

        tagRepository.save(tag);
    }

    public void removeUserTag(String tagLabel, Long userId) throws ResourceNotFoundException {
        var doesUserExist = doesUserExist(userId);
        if (!doesUserExist) {
            throw new ResourceNotFoundException("User", userId);
        }

        var tag = getByLabel(tagLabel);

        tag.removeUserTag(new UserTag(tag, userId));

        tagRepository.save(tag);
    }

    public void addQuestionTag(String tagLabel, Long questionId) throws ResourceNotFoundException {
        // Devrait être remplacé par une message kafka sur un topic type "QuestionCheckTopic"
        // en envoyant l'identifiant de la question.

        var doesQuestionExist = doesQuestionExist(questionId);

        if (doesQuestionExist) {
            throw new ResourceNotFoundException("Question", questionId);
        }

        var tag = getByLabel(tagLabel);

        tag.addUserTag(new UserTag(tag, questionId));

        tagRepository.save(tag);
    }

    public void removeQuestionTag(String tagLabel, Long questionId) throws ResourceNotFoundException {
        var doesQuestionExist = doesQuestionExist(questionId);
        if (!doesQuestionExist) {
            throw new ResourceNotFoundException("Question", questionId);
        }

        var tag = getByLabel(tagLabel);

        tag.removeQuestionTag(new QuestionTag(tag, questionId));

        tagRepository.save(tag);
    }

    public long deleteAllUserTags(Long userId) {
        return userTagRepository.deleteByUserId(userId);
    }

    public long deleteAllQuestionTags(Long questionId) {
        return questionTagRepository.deleteByQuestionId(questionId);
    }

    private boolean doesUserExist(Long userId) {
        // Devrait être remplacé par une message kafka sur un topic type "UserCheckTopic"
        // en envoyant l'identifiant de l'utilisateur.

        final String uri = "http://localhost:8080/users/" + userId + "/exists";

        RestTemplate restTemplate = new RestTemplate();
        return Boolean.TRUE.equals(restTemplate.getForObject(uri, Boolean.class));
    }

    private boolean doesQuestionExist(Long questionId) {
        final String uri = "http://localhost:8082/questions/" + questionId + "/exists";

        RestTemplate restTemplate = new RestTemplate();
        return Boolean.TRUE.equals(restTemplate.getForObject(uri, Boolean.class));
    }
}
