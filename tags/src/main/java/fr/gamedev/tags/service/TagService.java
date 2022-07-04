package fr.gamedev.tags.service;

import fr.gamedev.tags.controller.model.TagDTO;
import fr.gamedev.tags.data.Tag;
import fr.gamedev.tags.data.UserTag;
import fr.gamedev.tags.repository.TagRepository;
import fr.gamedev.tags.repository.UserTagRepository;
import fr.gamedev.tags.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserTagRepository userTagRepository;

    @Autowired
    private TagRepository tagRepository;

    public Tag getById(Long tagId) throws ResourceNotFoundException {
        var tag = tagRepository.findById(tagId);
        if (tag.isEmpty()) {
            throw new ResourceNotFoundException(Tag.class, tagId);
        }
        return tag.get();
    }

    public Tag create(TagDTO dto) throws ResourceNotFoundException {
        var category = categoryService.getById(dto.getCategoryId());

        var tag = new Tag(dto.getLabel());
        tag.setCategory(category);

        tagRepository.save(tag);

        return tag;
    }

    public void delete(Long tagId) {
        tagRepository.deleteById(tagId);
    }

    public Page<Tag> getAllPaged(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    public List<Tag> getByUserIdPaged(Long userId) {
        var userTags = userTagRepository.findAllByUserId(userId);

        var tags = new ArrayList<Tag>();

        for (int i = 0; i < userTags.size(); i++) {
            tags.add(userTags.get(i).getTag());
        }

        return tags;
    }

    public Tag addUserTag(Long tagId, Long userId) throws ResourceNotFoundException {
        var tag = getById(tagId);

        // Api call does user of Id exist ?

        var userTag = new UserTag(userId);

        tag.addUserTag(userTag);

        tagRepository.save(tag);

        return tag;
    }

    public void removeUserTag(Long tagId, Long userId) throws ResourceNotFoundException {
        var userTag = userTagRepository.findByUserIdAndTagId(tagId, userId);

        if (userTag.isEmpty()) {
            throw new ResourceNotFoundException(UserTag.class);
        }

        userTagRepository.delete(userTag.get());
    }
}
