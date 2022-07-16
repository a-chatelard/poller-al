package com.esgi.questions.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.esgi.questions.data.UserAnswer;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author djer1
 */
@RepositoryRestResource(collectionResourceRel = "userAnswer", path = "userAnswer")
public interface UserAnswerRepository extends PagingAndSortingRepository<UserAnswer, Long> {
    Optional<UserAnswer> findFirstByUserIdAndAnswerIdAndPoints(long userId, long answerId, Long points);

    Optional<UserAnswer> findFirstByUserIdAndAnswerIdAndPointsGreaterThanOrderByPoints(
            long userId,
            long answerId,
            long points);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserAnswer ua WHERE ua.userId = ?1")
    Integer deleteByUserId(long userId);
}
