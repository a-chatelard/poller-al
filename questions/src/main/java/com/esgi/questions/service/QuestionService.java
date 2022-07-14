package com.esgi.questions.service;

import java.util.Optional;

import com.esgi.questions.data.Answer;
import com.esgi.questions.data.UserAnswer;
import com.esgi.questions.repository.AnswerRepository;
import com.esgi.questions.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class QuestionService {

    /**
     * Amount of points per good answer.
     */
    private long points = 5;

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
    public Question create(final string questionContent, final boolean correctAnswer) {
        var question = new Question(questionContent);

        questionRepository.save(question);

        var anwser = new Answer(question, correctAnswer);

        answerRepository.save(anwser);

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
     * @return the result for create a userAnswer.
     */
    public String askQuestion(final long questionId, final long userId)
    {
        Optional<Question> question = questionRepository.findById(questionId);

        if (question.isEmpty()) {
            return "La question n'a pas été trouvée.";
        }

        if(doesUserExist(userId)) {
            return "L'utlisateur n'a pas été trouvé.";
        }

        Optional<Answer> answer = answerRepository.findByQuestionId(questionId);

        if (answer.isEmpty())
        {
            return "La réponse attendue n'a pas été trouvée.";
        }

        var userAnswer = new UserAnswer(answer.get(), userId);

        userAnswerRepository.save(userAnswer);

        return "La question a été posée à l'utilisateur.";
    }

    /**
     * Manage a user's answer for a question.
     * @param questionId    the question id.
     * @param userResponse  the user's response.
     * @param userId        the user id.
     * @return the result for the user's answer.
     */
    public String handleAnswer(final long questionId, final Boolean userResponse, final long userId) {
        Optional<Answer> answer = answerRepository.findByQuestionId(questionId);

        if (answer.isEmpty()) {
            return "La question n'a pas été trouvée.";
        }

        Optional<UserAnswer> userAnswer = userAnswerRepository.findFirstByUserIdAndAnswerIdAndPoints(userId,
                answer.get().getId(), null);

        if (userAnswer.isEmpty()) {
            return "Cette question n'a pas été posée à l'utilisateur.";
        }

        String response = "";

        if (userResponse == answer.get().getCorrectAnswer()) {
            Optional<UserAnswer> lastGoodAnswer = userAnswerRepository
                    .findFirstByUserIdAndAnswerIdAndPointsGreaterThanOrderByPoints(userId, answer.get().getId(), 0);
            if (lastGoodAnswer.isPresent()) {
                long lastPointsObtained = lastGoodAnswer.get().getPoints();
                lastPointsObtained /= 2;
                points = lastPointsObtained;
            }
            userAnswer.get().setPoints(points);

            response = "Bravo ! Vous avez trouvé !";
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
    public String deleteUserAnswer(final long userId) {
        long deleteUserAnswerCount = 0;

        deleteUserAnswerCount = userAnswerRepository.deleteByUserId(userId);

        return deleteUserAnswerCount + "UserAnswer supprimé(s)";
    }

    /**
     * Determine if a question exists.
     * @param questionId    the question id.
     * @return true if the question exists, otherwise false.
     */
    public boolean exist(final long questionId) {
        Optional<Question> question = questionRepository.findById(questionId);

        return !question.isEmpty();
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
}
