package com.esgi.questions.application.questionPosee.repositories;

import com.esgi.questions.domain.questionPosee.QuestionPosee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface QuestionPoseeReadRepository extends Repository<QuestionPosee, Long> {
    @Query("SELECT qp FROM QuestionPosee qp WHERE qp.id = ?1")
    Optional<QuestionPosee> getQuestionPoseeById(Long questionPoseeId);

    @Query("SELECT qp FROM QuestionPosee qp WHERE qp.utilisateurId = ?1")
    List<QuestionPosee> getQuestionsPoseesByUtilisateurId(Long utilisateurId);
}
