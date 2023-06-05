package com.esgi.questions.controller.question;

import com.esgi.questions.domain.question.Question;
import com.esgi.questions.domain.question.QuestionWriteRepository;
import com.esgi.questions.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public final class QuestionWriteController {

    @Autowired
    QuestionWriteRepository questionWriteRepository;

    @PostMapping
    long CreateQuestion(long ressourceId, long tagId, String libelle, boolean bonneReponse)
    {
        Question question = new Question(ressourceId, tagId, libelle, bonneReponse);
        return questionWriteRepository.create(question);
    }

    void acceptQuestion(long questionId, long regleAttributionPointsId) throws ResourceNotFoundException {
        Optional<Question> result = questionWriteRepository.getById(questionId);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException(Question.class, questionId);
        }

        Question question = result.get();
        question.accept(regleAttributionPointsId);

        questionWriteRepository.update(question);
    }

    void rejectQuestion(long questionId) throws ResourceNotFoundException {
        Optional<Question> result = questionWriteRepository.getById(questionId);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException(Question.class, questionId);
        }

        Question question = result.get();
        question.reject();

        questionWriteRepository.update(question);
    }
}
