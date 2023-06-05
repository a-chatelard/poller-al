package com.esgi.questions.application.question.repositories;

import com.esgi.questions.domain.question.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for read operations on a Question.
 */
public interface QuestionReadRepository extends Repository<Question, Long> {
    /**
     * Get all questions.
     * @return a list of Questions.
     */
    @Query("SELECT q FROM Question q")
    List<Question> getAllQuestions();

    /**
     * Get a question by its Id.
     * @return a Question.
     */
    @Query("SELECT q FROM Question q WHERE q.id = ?1")
    Optional<Question> getQuestionById(Long questionId);

    /**
     * Get a list of questions that share the same tagId.
     * @return a list of Questions.
     */
    @Query("SELECT q FROM Question q WHERE q.tagId = ?1")
    List<Question> getQuestionsByTagId(Long tagId);

    /**
     * Get a list of questions that share the same ressourceId.
     * @return a list of Questions.
     */
    @Query("SELECT q FROM Question q WHERE q.ressourceId = ?1")
    List<Question> getQuestionsByRessourceId(Long ressourceId);

    /**
     * Get all questions not yet validated.
     * @return a list of Questions.
     */
    @Query("SELECT q FROM Question q WHERE q.valid IS NULL")
    List<Question> getActiveQuestions();
}
