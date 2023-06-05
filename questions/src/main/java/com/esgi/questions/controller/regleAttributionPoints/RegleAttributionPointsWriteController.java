package com.esgi.questions.controller.regleAttributionPoints;

import com.esgi.questions.domain.regleAttributionPoints.RegleAttributionPoints;
import com.esgi.questions.domain.regleAttributionPoints.RegleAttributionPointsWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RequestMapping("/regles-attribution-points")
@RestController
public class RegleAttributionPointsWriteController {
    @Autowired
    private RegleAttributionPointsWriteRepository _repository;

    @PostMapping
    public Long createRegleAttributionPoints(final String libelle, final Integer pointsObtenus) {
        var regle = new RegleAttributionPoints(libelle, pointsObtenus);
        _repository.save(regle);
        return regle.getId();
    }

    @PutMapping("/{regleId}")
    public void updateRegle(final @PathVariable(name = "regleId") long regleId, final String libelle, final Integer pointsObtenus) {
        var regle = _repository.findById(regleId);
        if (regle.isPresent()) {
            regle.get().update(libelle, pointsObtenus);
            _repository.save(regle.get());
        } else {
            throw new EntityNotFoundException(String.format("Règle d'attribution de points d'ID %d non trouvé.", regleId));
        }
    }

    @DeleteMapping("/{regleId}")
    public void deleteRegle(final @PathVariable(name = "regleId") long regleId) {
         var regle = _repository.findById(regleId);
        if (regle.isPresent()) {
            _repository.delete(regle.get());
        } else {
            throw new EntityNotFoundException(String.format("Règle d'attribution de points d'ID %d non trouvé.", regleId));
        }
    }
}
