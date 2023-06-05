package com.esgi.questions.application.regleAttributionPoints.repositories;

import com.esgi.questions.domain.regleAttributionPoints.aggregate.RegleAttributionPoints;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface RegleAttributionPointsReadRepository extends Repository<RegleAttributionPoints, Long> {
    @Query("SELECT r FROM RegleAttributionPoints r WHERE r.id = ?1")
    Optional<RegleAttributionPoints> findById(Long regleId);

    @Query("SELECT r FROM RegleAttributionPoints r")
    List<RegleAttributionPoints> findAll();
}
