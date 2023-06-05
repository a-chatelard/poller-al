package com.esgi.questions.controller.regleAttributionPoints;

import com.esgi.questions.application.regleAttributionPoints.repositories.RegleAttributionPointsReadRepository;
import com.esgi.questions.domain.regleAttributionPoints.RegleAttributionPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller handling read requests on a regleAttributionPoints
 */
@RequestMapping("/regles-attribution-points")
@RestController
public class RegleAttributionPointsReadController {
    /**
     * Repository for read operations on a regleAttributionPoints
     */
    @Autowired
    private RegleAttributionPointsReadRepository repository;

    /**
     * Handles GET request to get a regleAttributionPoints by its Id
     * @param regleId the regleAttributionPoints Id
     * @return a regleAttributionPoints
     */
    @GetMapping("/{regleId}")
    public RegleAttributionPoints getRegleById(final @PathVariable(name = "regleId") long regleId) {
        var regle = repository.findById(regleId);
        return regle.orElse(null);
    }

    /**
     * Handles GET request to get all regleAttributionPoints
     * @return a list of regleAttributionPoints
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RegleAttributionPoints> getAllRegles() {
        return repository.findAll();
    }
}
