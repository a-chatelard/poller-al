package com.esgi.questions.service;

import java.util.Optional;

import com.esgi.questions.data.Answer;
import com.esgi.questions.data.Question;
import com.esgi.questions.data.UserAnswer;
import com.esgi.questions.repository.AnswerRepository;
import com.esgi.questions.repository.QuestionRepository;
import com.esgi.questions.repository.UserAnswerRepository;
import com.esgi.questions.service.exceptions.ResourceConflictException;
import com.esgi.questions.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public final class QuestionService {

    /**
     * Amount of points per good answer.
     */
    private final long points = 5;

    /**
     * The Answer repository.
     */
    @Autowired
    private AnswerRepository answerRepository;

    /**
     * The Question repository.
     */
    @Autowired
    private QuestionRepository questionRepository;

    /**
     * The UserAnswer repository.
     */
    @Autowired
    private UserAnswerRepository userAnswerRepository;

    /**
     * Create a question.
     * @param questionContent   the question content.
     * @param correctAnswer     the correct answer.
     * @return the created question entity.
     */
    public Question create(final String questionContent, final boolean correctAnswer) {
        var question = new Question(questionContent);

        var answer = new Answer(question, correctAnswer);

        question.setAnswer(answer);

        questionRepository.save(question);

        return question;
    }

    /**
     * Get a question by id.
     * @param questionId    the question id.
     * @return the related question.
     * @throws ResourceNotFoundException Question not found.
     */
    public Question getById(final long questionId)
            throws ResourceNotFoundException {
        var question = questionRepository.findById(questionId);

        if (question.isEmpty()) {
            throw new ResourceNotFoundException(Question.class, questionId);
        }

        return question.get();
    }

    /**
     * Get all questions with paging.
     * @param pageable  the paging parameters.
     * @return the list of questions paged.
     */
    public Page<Question> getAllPaged(final Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    /**
     * Delete a question.
     * @param questionId the question id.
     */
    public void deleteById(final long questionId) {
        questionRepository.deleteById(questionId);
    }

    /**
     * Ask a question to an user.
     * @param questionId    the question id.
     * @param userId        the user id.
     */
    public void askQuestion(final long questionId, final long userId) throws ResourceNotFoundException {
        Optional<Question> question = questionRepository.findById(questionId);

        if (question.isEmpty()) {
            throw new ResourceNotFoundException(Question.class, questionId);
        }

        if (!doesUserExist(userId)) {
            throw new ResourceNotFoundException("User", userId);
        }

        var userAnswer = new UserAnswer(question.get().getAnswer(), userId);

        userAnswerRepository.save(userAnswer);
    }

    /**
     * Manage a user's answer for a question.
     * @param questionId    the question id.
     * @param userResponse  the user's response.
     * @param userId        the user id.
     * @return the result for the user's answer.
     */
    public String handleAnswer(final long questionId, final Boolean userResponse, final long userId)
            throws ResourceNotFoundException, ResourceConflictException {
        Optional<Answer> answer = answerRepository.findByQuestionId(questionId);

        if (answer.isEmpty()) {
            throw new ResourceNotFoundException(Question.class, questionId);
        }

        Optional<UserAnswer> userAnswer = userAnswerRepository.findFirstByUserIdAndAnswerIdAndPoints(userId,
                answer.get().getId(), null);

        if (userAnswer.isEmpty()) {
            throw new ResourceConflictException("Cette question n'a pas été posée à l'utilisateur.");
        }

        String response;

        var obtainedPoints = points;
        if (userResponse == answer.get().getCorrectAnswer()) {
            Optional<UserAnswer> lastGoodAnswer = userAnswerRepository
                    .findFirstByUserIdAndAnswerIdAndPointsGreaterThanOrderByPoints(userId, answer.get().getId(), 0);
            if (lastGoodAnswer.isPresent()) {
                long lastPointsObtained = lastGoodAnswer.get().getPoints();
                lastPointsObtained /= 2;
                obtainedPoints = lastPointsObtained;
            }
            userAnswer.get().setPoints(obtainedPoints);

            response = "Bravo ! Vous avez trouvé ! Vous obtenez " + obtainedPoints + " points.";
        } else {
            userAnswer.get().setPoints(0);
            response = "Oops ! Ce n'est pas correct.";
        }

        userAnswerRepository.save(userAnswer.get());

        return response;
    }

    /**
     * Delete an user's answer.
     * @param userId    the user id of the answer.
     * @return the number of userAnswer deleted.
     */
    public String deleteUserAnswers(final long userId) {
        var deleteUserAnswerCount = userAnswerRepository.deleteByUserId(userId);

        return deleteUserAnswerCount + " UserAnswer supprimé(s)";
    }

    /**
     * Determine if a question exists.
     * @param questionId    the question id.
     * @return true if the question exists, otherwise false.
     */
    public boolean exist(final long questionId) {
        return questionRepository.existsById(questionId);
    }

    /**
     * Check if an user exists.
     * @param userId    the user id.
     * @return true if the user exists, otherwise false.
     */
    private boolean doesUserExist(final long userId) {
        final String uri = "http://localhost:8080/users/" + userId + "/exists";

        RestTemplate restTemplate = new RestTemplate();
        return Boolean.TRUE.equals(restTemplate.getForObject(uri, Boolean.class));
    }

    /**
     * Get all the questions related to a user.
     * @param userId the user Id.
     * @param pageable the paging parameters.
     * @return a paged list of question.
     */
    public Page<Question> getUserQuestions(final long userId, final Pageable pageable) {
        return questionRepository.findByUserId(userId, pageable);
    }
}
