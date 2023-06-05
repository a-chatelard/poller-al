package com.esgi.questions.controller.questionPosee;

import com.esgi.questions.UtilisateurGateway;
import com.esgi.questions.domain.questionPosee.questionPoseeWriteRepository;
import com.esgi.questions.domain.questionPosee.aggregate.QuestionPosee;
import org.springframework.web.bind.annotation.PostMapping;
import com.esgi.questions.service.exceptions.ResourceNotFoundException;

import java.util.Optional;

public class QuestionPoseeWriteController {
    /**
     * Service gérant les requêtes concernant la modification des questionPosee.
     */
    questionPoseeWriteRepository m_questionPoseeWriteRepository;

    UtilisateurGateway m_utilisateurGateway;

    /**
     * Create a question asked to the user.
     *
     * @param questionId    the question Id.
     * @param utilisateurId the user Id.
     * @return the created questionPosee Id.
     */
    @PostMapping
    public long createQuestionPosee(long questionId, long utilisateurId) throws Exception {
       if( m_utilisateurGateway.isUserExisting(utilisateurId)){
        QuestionPosee questionPosee = new QuestionPosee(questionId, utilisateurId);
        m_questionPoseeWriteRepository.save(questionPosee);
        return questionPosee.getId();} else {
           throw new Exception("The user doesn't exist");
       }

    }

    /**
     * Answer a questionPosee.
     *
     * @param questionPoseeId    the questionPosee Id.
     * @param reponseUtilisateur the user answer.
     */
    @PostMapping
    public void answerQuestionPosee(long questionPoseeId, boolean reponseUtilisateur) throws ResourceNotFoundException {
        Optional<QuestionPosee> questionPosee = m_questionPoseeWriteRepository.findById(questionPoseeId);
        if (questionPosee.isPresent()) {
            questionPosee.get().answer(reponseUtilisateur);
            m_questionPoseeWriteRepository.save(questionPosee.get());

        } else {
            throw new ResourceNotFoundException(questionPosee.getClass());
        }

    }
}
