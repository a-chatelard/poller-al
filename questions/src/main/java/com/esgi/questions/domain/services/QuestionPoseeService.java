package com.esgi.questions.domain.services;

import com.esgi.questions.domain.common.exceptions.DomainException.DomainException;
import com.esgi.questions.domain.question.Question;
import com.esgi.questions.domain.questionPosee.QuestionPosee;
import com.esgi.questions.domain.regleAttributionPoints.RegleAttributionPoints;

public class QuestionPoseeService {
    public static Integer answerQuestionPosee(
            QuestionPosee questionPosee,
            Question question,
            RegleAttributionPoints regleAttributionPoints,
            Boolean reponseUtilisateur) throws DomainException {
        var pointsObtenus = 0;
        if (question.getBonneReponse() == reponseUtilisateur) {
            var lastPointsObtenus = questionPosee.getLastPointsObtenus();
            pointsObtenus = lastPointsObtenus.map(integer -> integer / 2).orElseGet(regleAttributionPoints::getPointsObtenus);
        }
        questionPosee.answer(reponseUtilisateur, pointsObtenus);

        return pointsObtenus;
    }
}
