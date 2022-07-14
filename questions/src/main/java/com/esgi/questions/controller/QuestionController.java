package com.esgi.questions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esgi.questions.service.QuestionService;

/**
 * @author djer1
 */
@RequestMapping("/question")
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
    public Category doesQuestionExist(final @PathVariable(name = "questionId") long questionId) {
        return questionService.exist(questionId);
    }

    /**
     * Get a question by Id.
     * @param questionId    the question id.
     * @return the related question.
     * @throws ResourceNotFoundException Question not found.
     */
    @GetMapping("/{questionId}")
    public Category getQuestionById(final @PathVariable(name = "questionId") long questionId)
            throws ResourceNotFoundException {
        return questionService.getById(questionId);
    }

    /**
     * Get all the questions with paging.
     * @param pageable
     * @return the list of questions paged.
     */
    @GetMapping
    public Page<Category> getAllQuestionsPaged(final Pageable pageable) {
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
     * @return the result of asking the question.
     */
    @GetMapping("/ask")
    public String askQuestion(@RequestParam final long questionId, @RequestParam final long userId) {
        String result = questionService.askQuestion(questionId, userId);
        return result;
    }

    /**
     * Get a response of an user's answer.
     * @param questionId the question id.
     * @param answer     the user answer.
     * @param userId     the user id.
     * @return the response of the user's answer.
     */
    @GetMapping("/response")
    public String answer(@RequestParam
    final long questionId, @RequestParam
    final Boolean answer, @RequestParam
    final long userId) {

        String response = questionService.handleAnswer(questionId, answer, userId);
        return response;
    }

    /**
     * Delete userAnwsers of an user.
     * @param userId     the user id
     * @return the number of the userAnswers deleted.
     */
    @DeleteMapping("/user/{userId}")
    public String deleteUser(
            @PathVariable(name = "userId") final long userId) {

        String response = questionService.deleteByUserId(userId);
        return response;
    }
}
