package com.esgi.questions.controller.question;

import com.esgi.questions.application.question.repositories.QuestionReadRepository;
import com.esgi.questions.domain.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Controller handling read requests on a question
 */
@RequestMapping("/questions")
@RestController
public final class QuestionReadController
{
    /**
     * Repository for read operations on a question
     */
    @Autowired
    private QuestionReadRepository repository;

    /**
     * Handles GET request to get all questions
     * @return a list of questions
     */
    @GetMapping
    public List<Question> getAllQuestions()
    {
        return repository.getAllQuestions();
    }

    /**
     * Handles GET request to get a question by its Id
     * @param questionId the question Id
     * @return a question
     */
    @GetMapping("/{questionId}")
    public Optional<Question> getQuestionById(final @PathVariable(name = "questionId") Long questionId)
    {
        return repository.getQuestionById(questionId);
    }

    /**
     * Handles GET request to get a list of questions that share the same tagId
     * @param tagId the tagId of the question
     * @return a list of questions
     */
    @GetMapping("/tag/{tagId}")
    public List<Question> getQuestionsByTagId(final @PathVariable(name = "tagId") Long tagId)
    {
        return repository.getQuestionsByTagId(tagId);
    }

    /**
     * Handles GET request to get a list of questions that share the same ressourceId
     * @param ressourceId the ressourceId of the question
     * @return an array of questions
     */
    @GetMapping("/ressource/{ressourceId}")
    public List<Question> getQuestionsByRessourceId(final @PathVariable(name = "ressourceId") Long ressourceId)
    {
        return repository.getQuestionsByRessourceId(ressourceId);
    }

    /**
     * Handles GET request to get all questions not validated
     * @return a list of questions
     */
    @GetMapping("/active")
    public List<Question> getQuestionsNonValidees()
    {
        return repository.getActiveQuestions();
    }
}
