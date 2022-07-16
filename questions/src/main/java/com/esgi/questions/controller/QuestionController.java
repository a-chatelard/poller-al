package com.esgi.questions.controller;

import com.esgi.questions.data.Question;
import com.esgi.questions.service.exceptions.ResourceConflictException;
import com.esgi.questions.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.esgi.questions.service.QuestionService;

/**
 * @author djer1
 */
@RequestMapping("/questions")
@RestController
public final class QuestionController {
    /**
     * Service gérant les requêtes concernant les réponses.
     */
    @Autowired
    private QuestionService questionService;

    /**
     * Create a question.
     * @param questionContent   the question content.
     * @param correctAnswer     the correct answer.
     * @return the created question entity.
     */
    @PostMapping
    public Question createQuestion(final String questionContent, final boolean correctAnswer) {
        return questionService.create(questionContent, correctAnswer);
    }

    /**
     * Check if a question exists.
     * @param questionId    the question id.
     * @return true if the question exists, otherwise false.
     */
    @GetMapping("/{questionId}/exists")
    public boolean doesQuestionExist(final @PathVariable(name = "questionId") long questionId) {
        return questionService.exist(questionId);
    }

    /**
     * Get a question by Id.
     * @param questionId    the question id.
     * @return the related question.
     * @throws ResourceNotFoundException Question not found.
     */
    @GetMapping("/{questionId}")
    public Question getQuestionById(final @PathVariable(name = "questionId") long questionId)
            throws ResourceNotFoundException {
        return questionService.getById(questionId);
    }

    /**
     * Get all the questions with paging.
     * @param pageable the paging parameters.
     * @return the list of questions paged.
     */
    @GetMapping
    public Page<Question> getAllQuestionsPaged(final Pageable pageable) {
        return questionService.getAllPaged(pageable);
    }

    /**
     * Delete a question.
     * @param questionId    the question id.
     */
    @DeleteMapping("/{questionId}")
    public void deleteQuestion(final @PathVariable(name = "questionId") long questionId) {
        questionService.deleteById(questionId);
    }

    /**
     * Ask a question to an user.
     * @param questionId    the question id.
     * @param userId        the user id.
     */
    @PostMapping("/{questionId}/user/{userId}/ask")
    public void askQuestion(final @PathVariable(name = "questionId") long questionId,
                              final @PathVariable(name = "userId") long userId)
            throws ResourceNotFoundException {
        questionService.askQuestion(questionId, userId);
    }

    /**
     * Get a response of an user's answer.
     * @param questionId the question id.
     * @param answer     the user answer.
     * @param userId     the user id.
     * @return the response of the user's answer.
     */
    @PutMapping("/{questionId}/user/{userId}")
    public String answer(
            final @PathVariable(name = "questionId") long questionId,
            final @PathVariable(name = "userId") long userId,
            @RequestParam final Boolean answer)
            throws ResourceConflictException, ResourceNotFoundException {
        return questionService.handleAnswer(questionId, answer, userId);
    }

    /**
     * Delete userAnwsers of an user.
     * @param userId     the user id
     * @return the number of the userAnswers deleted.
     */
    @DeleteMapping("/user/{userId}")
    public String deleteUserAnswers(
            @PathVariable(name = "userId") final long userId) {
        return questionService.deleteUserAnswers(userId);
    }
}
