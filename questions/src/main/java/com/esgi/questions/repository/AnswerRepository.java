package com.esgi.questions.repository;

import java.util.Optional;

import com.esgi.questions.data.Answer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author djer1
 */
@RepositoryRestResource(collectionResourceRel = "answer", path = "answer")
public interface AnswerRepository extends PagingAndSortingRepository<Answer, Long> {
    Optional<Answer> findByQuestionId(long questionId);
}
