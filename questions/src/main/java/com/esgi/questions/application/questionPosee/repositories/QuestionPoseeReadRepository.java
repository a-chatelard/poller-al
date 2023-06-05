package com.esgi.questions.application.questionPosee.repositories;

import com.esgi.questions.domain.questionPosee.aggregate.QuestionPosee;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "questionPosee", path = "questionPosee")
public interface QuestionPoseeReadRepository {
    Optional<QuestionPosee> getQuestionPoseeById(Long questionPoseeId);

    Optional<QuestionPosee[]> getQuestionPoseeByUtilisateurId(Long utilisateurId);
}
