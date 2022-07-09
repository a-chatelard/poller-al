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
     * Gets a response of an user's answer.
     *
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
     *
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