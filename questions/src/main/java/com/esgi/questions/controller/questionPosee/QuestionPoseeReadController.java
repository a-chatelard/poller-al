package com.esgi.questions.controller.questionPosee;

import com.esgi.questions.application.questionPosee.repositories.QuestionPoseeReadRepository;
import com.esgi.questions.domain.questionPosee.aggregate.QuestionPosee;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

public class QuestionPoseeReadController {

    /**
     * Service gérant les requêtes concernant la lecture des questionPosee.
     */
    QuestionPoseeReadRepository m_questionPoseeReadRepository;

    /**
     * Get a questionPosee.
     *
     * @param questionPoseeId the questionPosee Id.
     * @return the Id of the questionPosee
     */
    @GetMapping("/{questionPoseeId}")
    public QuestionPosee getQuestionPoseeById(long questionPoseeId) {

        Optional<QuestionPosee> questionPosee = m_questionPoseeReadRepository.getQuestionPoseeById(questionPoseeId);
        return questionPosee.orElse(null);
    }

    /**
     * Get all the questionPosee for an user.
     *
     * @param utilisateurId the user Id.
     * @return an array of qiestionPosee
     */
    @GetMapping("/{utilisateurId}")
    public QuestionPosee[] getQuestionPoseeByUtilisateurId(long utilisateurId) {
        Optional<QuestionPosee[]> questionsUtilisateur = m_questionPoseeReadRepository.getQuestionPoseeByUtilisateurId(utilisateurId);
        return questionsUtilisateur.orElse(null);
    }
}



