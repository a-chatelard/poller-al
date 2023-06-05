package com.esgi.questions.controller.question;

import com.esgi.questions.application.question.repositories.QuestionReadRepository;
import com.esgi.questions.domain.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/questions")
@RestController
public final class QuestionReadController
{
    @Autowired
    private QuestionReadRepository repository;

    @GetMapping
    public List<Question> getAllQuestions()
    {
        return repository.getAllQuestions();
    }

    @GetMapping("/{questionId}")
    public Optional<Question> getQuestionById(final @PathVariable(name = "questionId") Long questionId)
    {
        return repository.getQuestionById(questionId);
    }

    @GetMapping("/tag/{tagId}")
    public List<Question> getQuestionsByTagId(final @PathVariable(name = "tagId") Long tagId)
    {
        return repository.getQuestionsByTagId(tagId);
    }

    @GetMapping("/ressource/{ressourceId}")
    public List<Question> getQuestionsByRessourceId(final @PathVariable(name = "ressourceId") Long ressourceId)
    {
        return repository.getQuestionsByRessourceId(ressourceId);
    }

    @GetMapping("/active")
    public List<Question> getQuestionsNonValidees()
    {
        return repository.getActiveQuestions();
    }
}
