package com.esgi.questions.domain.questionPosee;

import com.esgi.questions.domain.questionPosee.aggregate.QuestionPosee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "questionPosee", path = "questionPosee")
public interface questionPoseeWriteRepository extends CrudRepository<QuestionPosee, Long> {

}
