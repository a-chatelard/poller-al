package com.esgi.questions.domain.services;

import com.esgi.questions.domain.common.exceptions.DomainException.DomainException;
import com.esgi.questions.domain.question.Question;
import com.esgi.questions.domain.questionPosee.QuestionPosee;
import com.esgi.questions.domain.regleAttributionPoints.RegleAttributionPoints;

/**
 * Service domaine de gestion d'une réponse à une question posée.
 */
public class QuestionPoseeService {

    /**
     * Répond à une question donnée et assigne le nombre de points obtenu
     * @param questionPosee Question posée à l'utilisateur.
     * @param question Question à laquelle l'utilisateur a répondu.
     * @param regleAttributionPoints Règle d'attribution de points de la question.
     * @param reponseUtilisateur Réponse donnée par l'utilisateur.
     * @return Le nombre de points obtenus par l'utilisateur.
     * @throws DomainException Exception soulevée si la question posée n'a pas de réponse en attente.
     */
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
