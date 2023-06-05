package com.esgi.questions.application;

import com.esgi.questions.domain.question.aggregate.Question;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "question", path = "question")
public interface IQuestionReadRepository extends PagingAndSortingRepository<Question, Long> {
    Question[] getAllQuestions();
    Question[] getQuestionsByTagId(long tagId);
    Question[] getQuestionsByRessourceId(long ressourceId);
    Question[] getQuestionsById(long questionId);
    Question[] getQuestionsNonValidees();

}
