package com.esgi.questions.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.esgi.questions.data.UserAnswer;

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
}
