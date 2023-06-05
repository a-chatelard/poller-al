package com.esgi.questions.controller.regleAttributionPoints;

import com.esgi.questions.application.regleAttributionPoints.repositories.RegleAttributionPointsReadRepository;
import com.esgi.questions.domain.regleAttributionPoints.RegleAttributionPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/regles-attribution-points")
@RestController
public class RegleAttributionPointsReadController {
    @Autowired
    private RegleAttributionPointsReadRepository repository;

    @GetMapping("/{regleId}")
    public RegleAttributionPoints getRegleById(final @PathVariable(name = "regleId") long regleId) {
        var regle = repository.findById(regleId);
        return regle.orElse(null);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RegleAttributionPoints> getAllRegles() {
        return repository.findAll();
    }
}