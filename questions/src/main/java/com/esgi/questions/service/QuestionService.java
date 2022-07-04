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
     * The UserAnswer repository.
     */
    @Autowired
    private UserAnswerRepository userAnswerRepository;

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

        String response 
        
        = "";

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

    private QuestionService() {

    }
}
