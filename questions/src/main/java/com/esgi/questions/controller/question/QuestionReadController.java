package com.esgi.questions.controller.question;

import com.esgi.questions.application.question.repositories.QuestionReadRepository;
import com.esgi.questions.domain.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Question> getQuestionById(final @PathVariable(name = "questionId") Long questionId)
    {
        return repository.getQuestionById(questionId);
    }

    @GetMapping()
    Question[] getQuestionsByTagId(long tagId)
    {
        return repository.getQuestionsByTagId(tagId);
    }

    Question[] getQuestionsByRessourceId(long ressourceId)
    {
        return repository.getQuestionsByTagId(ressourceId);
    }

    Question[] getQuestionsNonValidees()
    {
        return repository.getQuestionsNonValidees();
    }
}
