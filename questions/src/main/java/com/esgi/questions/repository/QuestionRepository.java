package com.esgi.questions.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.esgi.questions.data.Question;

/**
 * @author djer1
 */
@RepositoryRestResource(collectionResourceRel = "question", path = "question")
public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
    @Query("SELECT q FROM Question q JOIN UserAnswer ua ON ua.userId = ?1 AND q.id = ua.answer.question.id")
    Page<Question> findByUserId(long userId, Pageable pageable);
}
