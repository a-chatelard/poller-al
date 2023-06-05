package com.esgi.questions.controller.question;

import com.esgi.questions.application.regleAttributionPoints.repositories.RegleAttributionPointsReadRepository;
import com.esgi.questions.domain.question.Question;
import com.esgi.questions.domain.question.QuestionWriteRepository;
import com.esgi.questions.gateways.RessourceGateway;
import com.esgi.questions.gateways.TagGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/questions")
@RestController
public final class QuestionWriteController {
    @Autowired
    QuestionWriteRepository repository;
    @Autowired
    RegleAttributionPointsReadRepository regleAttributionPointsReadRepository;
    @Autowired
    TagGateway tagGateway;
    @Autowired
    RessourceGateway ressourceGateway;

    @PostMapping
    public Long createQuestion(Long ressourceId, Long tagId, String libelle, boolean bonneReponse)
    {
        if (!ressourceGateway.doesRessourceExist(ressourceId)) {
            throw new ResourceNotFoundException(String.format("Ressource d'ID %d non trouvée", ressourceId));
        }
        if (!tagGateway.doesTagExist(tagId)) {
            throw new ResourceNotFoundException(String.format("Tag d'ID %d non trouvé", tagId));
        }

        var question = new Question(ressourceId, tagId, libelle, bonneReponse);

        repository.save(question);

        return question.getId();
    }

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
