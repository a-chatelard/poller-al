package com.esgi.questions.controller.regleAttributionPoints;

import com.esgi.questions.domain.regleAttributionPoints.RegleAttributionPoints;
import com.esgi.questions.domain.regleAttributionPoints.RegleAttributionPointsWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

/**
 * Controller handling write requests on a regleAttributionPoints
 */
@RequestMapping("/regles-attribution-points")
@RestController
public class RegleAttributionPointsWriteController {
    /**
     * Repository for write operations on a regleAttributionPoints
     */
    @Autowired
    private RegleAttributionPointsWriteRepository _repository;

    /**
     * Handles POST request to create a regleAttributionPoints
     */
    @PostMapping
    public Long createRegleAttributionPoints(final String libelle, final Integer pointsObtenus) {
        var regle = new RegleAttributionPoints(libelle, pointsObtenus);

        _repository.save(regle);

        return regle.getId();
    }

    /**
     * Handles PUT request to update a regleAttributionPoints
     */
    @PutMapping("/{regleId}")
    public void updateRegle(final @PathVariable(name = "regleId") long regleId, final String libelle, final Integer pointsObtenus) {
        var regle = _repository.findById(regleId);
        if (regle.isEmpty()) {
            throw new EntityNotFoundException(String.format("Règle d'attribution de points d'ID %d non trouvé.", regleId));
        }

        regle.get().update(libelle, pointsObtenus);

        _repository.save(regle.get());
    }

    /**
     * Handles DELETE request to delete a regleAttributionPoints
     */
    @DeleteMapping("/{regleId}")
    public void deleteRegle(final @PathVariable(name = "regleId") long regleId) {
        var regle = _repository.findById(regleId);
        if (regle.isEmpty()) {
            throw new EntityNotFoundException(String.format("Règle d'attribution de points d'ID %d non trouvé.", regleId));
        }

        _repository.delete(regle.get());
    }
}
