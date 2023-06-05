package com.esgi.questions.controller.question;

import com.esgi.questions.application.IQuestionReadRepository;
import com.esgi.questions.domain.question.aggregate.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class QuestionReadController
{
    @Autowired
    private IQuestionReadRepository questionReadRepository;

    Question[] getAllQuestions()
    {
        return questionReadRepository.getAllQuestions();
    }

    Question[] getQuestionsByTagId(long tagId)
    {
        return questionReadRepository.getQuestionsByTagId(tagId);
    }

    Question[] getQuestionsByRessourceId(long ressourceId)
    {
        return questionReadRepository.getQuestionsByTagId(ressourceId);
    }

    Question[] getQuestionsById(long questionId)
    {
        return questionReadRepository.getQuestionsById(questionId);
    }

    Question[] getQuestionsNonValidees()
    {
        return questionReadRepository.getQuestionsNonValidees();
    }
}
