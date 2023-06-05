package com.esgi.questions.domain.question;

import com.esgi.questions.domain.question.aggregate.Question;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "question", path = "question")
public interface IQuestionWriteRepository extends PagingAndSortingRepository<Question, Long> {
    public Optional<Question> getById(long questionId);
    public long create(Question question);
    public void update(Question question);
    public void delete(Question question);
}
