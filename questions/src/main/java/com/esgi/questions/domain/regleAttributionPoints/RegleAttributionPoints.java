package com.esgi.questions.domain.regleAttributionPoints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RegleAttributionPoints {
    @GeneratedValue()
    @Id
    private Long id;
    private String libelle;
    private Integer pointsObtenus;

    public RegleAttributionPoints() {
    }

    public Long getId() {
        return id;
    }
    public String getLibelle() {
        return libelle;
    }
    public Integer getPointsObtenus() {
        return pointsObtenus;
    }

    public RegleAttributionPoints(String libelle, Integer pointsObtenus) {
        this.libelle = libelle;
        this.pointsObtenus = pointsObtenus;
    }
    public void update(String libelle, Integer pointsObtenus) {
        this.libelle = libelle;
        this.pointsObtenus = pointsObtenus;
    }
}

