package com.esgi.questions.controller.question;

import com.esgi.questions.application.regleAttributionPoints.repositories.RegleAttributionPointsReadRepository;
import com.esgi.questions.domain.question.Question;
import com.esgi.questions.domain.question.QuestionWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

/**
 * Controller handling write requests on a question
 */
@RequestMapping("/questions")
@RestController
public final class QuestionWriteController {
    /**
     * Repository for write operations on a question
     */
    @Autowired
    QuestionWriteRepository repository;

    /**
     * Repository for read operations on a regleAttributionPoints
     */
    @Autowired
    RegleAttributionPointsReadRepository regleAttributionPointsReadRepository;

    /**
     * Handles POST request to create a question
     */
    @PostMapping
    public Long createQuestion(Long ressourceId, Long tagId, String libelle, boolean bonneReponse)
    {
        var question = new Question(ressourceId, tagId, libelle, bonneReponse);

        // Check tag and ressource using gateways

        repository.save(question);

        return question.getId();
    }

    /**
     * Handles PATCH request to accept a question
     */
    @PatchMapping("/{questionId}/accept")
    public void acceptQuestion(final @PathVariable(name = "questionId") Long questionId, Long regleAttributionPointsId) throws ResourceNotFoundException {
        var question = repository.findById(questionId);
        if (question.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Question d'ID %d non trouvé.", questionId));
        }

        var regle = regleAttributionPointsReadRepository.findById(regleAttributionPointsId);
        if (regle.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Règle d'attribution de points d'ID %d non trouvée.", regleAttributionPointsId));
        }

        question.get().accept(regleAttributionPointsId);

        repository.save(question.get());
    }

    /**
     * Handles PATCH request to reject a question
     */
    @PatchMapping("/{questionId}/reject")
    public void rejectQuestion(final @PathVariable(name = "questionId") Long questionId) throws ResourceNotFoundException {
        var question = repository.findById(questionId);
        if (question.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Question d'ID %d non trouvé.", questionId));
        }

        question.get().reject();

        repository.save(question.get());
    }
}
