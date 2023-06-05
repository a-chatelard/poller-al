package com.esgi.questions.domain.regleAttributionPoints.repositories;

import com.esgi.questions.domain.regleAttributionPoints.aggregate.RegleAttributionPoints;
import org.springframework.data.repository.CrudRepository;

public interface RegleAttributionPointsWriteRepository extends CrudRepository<RegleAttributionPoints, Long> {
}
