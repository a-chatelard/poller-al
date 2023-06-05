package com.esgi.questions.application.regleAttributionPoints.repositories;

import com.esgi.questions.domain.regleAttributionPoints.RegleAttributionPoints;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for read operations on a RegleAttributionPoints.
 */
public interface RegleAttributionPointsReadRepository extends Repository<RegleAttributionPoints, Long> {
    /**
     * Get a regleAttributionPoints by its Id.
     * @return a RegleAttributionPoints.
     */
    @Query("SELECT r FROM RegleAttributionPoints r WHERE r.id = ?1")
    Optional<RegleAttributionPoints> findById(Long regleId);

    /**
     * Get all regleAttributionPoints.
     * @return a list of RegleAttributionPoints.
     */
    @Query("SELECT r FROM RegleAttributionPoints r")
    List<RegleAttributionPoints> findAll();
}
