package com.esgi.questions.domain.regleAttributionPoints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RegleAttributionPoints {
    /**
     * The RegleAttributionPoints Id.
     */
    @GeneratedValue()
    @Id
    private Long id;
    /**
     * The RegleAttributionPoints wording.
     */
    private String libelle;
    /**
     * The number of points given by the RegleAttributionPoints.
     */
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

    /**
     * Update the RegleAttributionPoints.
     * @param libelle The RegleAttributionPoints wording.
     * @param pointsObtenus The number of points given by the RegleAttributionPoints.
     */
    public void update(String libelle, Integer pointsObtenus) {
        this.libelle = libelle;
        this.pointsObtenus = pointsObtenus;
    }
}

