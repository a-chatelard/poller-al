package com.esgi.questions.controller.questionPosee;

import com.esgi.questions.gateways.UtilisateurGateway;
import com.esgi.questions.application.question.repositories.QuestionReadRepository;
import com.esgi.questions.application.regleAttributionPoints.repositories.RegleAttributionPointsReadRepository;
import com.esgi.questions.domain.common.exceptions.DomainException.DomainException;
import com.esgi.questions.domain.questionPosee.QuestionPoseeWriteRepository;
import com.esgi.questions.domain.questionPosee.QuestionPosee;
import com.esgi.questions.domain.services.QuestionPoseeService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class QuestionPoseeWriteController {
    /**
     * Service gérant les requêtes concernant la modification des questionPosee.
     */
    QuestionPoseeWriteRepository repository;
    QuestionReadRepository questionReadRepository;
    RegleAttributionPointsReadRepository regleAttributionPointsReadRepository;
    UtilisateurGateway utilisateurGateway;

    /**
     * Create a question asked to the user.
     *
     * @param questionId    the question Id.
     * @param utilisateurId the user Id.
     * @return the created questionPosee Id.
     */
    @PostMapping
    public Long createQuestionPosee(Long questionId, Long utilisateurId) throws DomainException, ResourceNotFoundException {
        if (!utilisateurGateway.doesUserExist(utilisateurId)) {
            throw new ResourceNotFoundException(String.format("Utilisateur d'ID %d non trouvé", utilisateurId));
        }

        var question = questionReadRepository.getQuestionById(questionId);
        if (question.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Question d'ID %d non trouvée", questionId));
        }

        if (!Boolean.TRUE.equals(question.get().getValid())) {
            throw new DomainException(String.format("Cette question n'a pas été validée.", questionId));
        }

        QuestionPosee questionPosee = new QuestionPosee(questionId, utilisateurId);

        repository.save(questionPosee);

        return questionPosee.getId();
    }

    /**
     * Answer a questionPosee.
     *
     * @param questionPoseeId    the questionPosee Id.
     * @param reponseUtilisateur the user answer.
     */
    @PostMapping("/{questionPoseeId}/answer")
    public Integer answerQuestionPosee(final @PathVariable(name = "questionPoseeId") Long questionPoseeId, Boolean reponseUtilisateur) throws DomainException, ResourceNotFoundException {
        var questionPosee =  repository.findById(questionPoseeId);

        if (questionPosee.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Question posée d'ID %d non trouvée", questionPoseeId));
        }

        var question = questionReadRepository.getQuestionById(questionPosee.get().getQuestionId());
        if (question.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Question d'ID %d non trouvée", questionPosee.get().getQuestionId()));
        }

        var regleAttributionPoints = regleAttributionPointsReadRepository.findById(question.get().getRegleAttributionPointsId());
        if (regleAttributionPoints.isEmpty()) {
            throw new ResourceNotFoundException(String.format("La question d'ID %d n'a pas de règle d'attribution de points définie.", question.get().getId()));
        }

        var pointsObtenus = QuestionPoseeService.answerQuestionPosee(questionPosee.get(), question.get(), regleAttributionPoints.get(), reponseUtilisateur);

        repository.save(questionPosee.get());

        return pointsObtenus;
    }
}
