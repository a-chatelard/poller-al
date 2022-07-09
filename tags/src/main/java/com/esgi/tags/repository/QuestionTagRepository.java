package com.esgi.tags.repository;

import com.esgi.tags.data.QuestionTag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "questionTag", path = "questionTag")
public interface QuestionTagRepository extends PagingAndSortingRepository<QuestionTag, Long> {
    long deleteByQuestionId(Long questionId);
}
