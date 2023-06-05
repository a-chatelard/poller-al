package com.esgi.questions.application.question.repositories;

import com.esgi.questions.domain.question.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface QuestionReadRepository extends Repository<Question, Long> {
    @Query("SELECT q FROM Question q")
    List<Question> getAllQuestions();

    @Query("SELECT q FROM Question q WHERE q.id = ?1")
    Optional<Question> getQuestionById(Long questionId);

    @Query("SELECT q FROM Question q WHERE q.tagId = ?1")
    List<Question> getQuestionsByTagId(Long tagId);

    @Query("SELECT q FROM Question q WHERE q.ressourceId = ?1")
    List<Question> getQuestionsByRessourceId(Long ressourceId);

    @Query("SELECT q FROM Question q WHERE q.valid = ?1")
    List<Question> getQuestionsByValid(Boolean valid);
}
