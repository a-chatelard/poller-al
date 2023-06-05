package com.esgi.questions.domain.question;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
    /**
     * The question Id.
     */
    @GeneratedValue
    @Id
    private Long id;
    /**
     * The ressourceId of the ressource associated with the question.
     */
    private Long ressourceId;
    /**
     * The tagId of the tag associated with the question.
     */
    private Long tagId;
    /**
     * The regleAttributionPointsId of the regleAttributionPoints associated with the question
     */
    @Nullable
    private Long regleAttributionPointsId;
    /**
     * The question wording.
     */
    private String libelle;
    /**
     * The correct answer to the question.
     */
    private Boolean bonneReponse;
    /**
     * The validation state of the question.
     */
    @Nullable
    private Boolean valid;

    public Question() {}

    public Question(Long ressourceId, Long tagId, String libelle, boolean bonneReponse)
    {
        this.ressourceId = ressourceId;
        this.tagId = tagId;
        this.libelle = libelle;
        this.bonneReponse = bonneReponse;
    }

    public Long getId() {
        return id;
    }
    public Long getRessourceId() {
        return ressourceId;
    }
    public Long getTagId() {
        return tagId;
    }
    public Long getRegleAttributionPointsId() {
        return regleAttributionPointsId;
    }
    public String getLibelle() {
        return libelle;
    }
    public Boolean getBonneReponse() {
        return bonneReponse;
    }
    public Boolean getValid() {
        return valid;
    }

    /**
     * Accept the question and associate it with a regleAttributionPoints.
     */
    public void accept(long regleAttributionPointsId)
    {
        this.valid = true;
        this.regleAttributionPointsId = regleAttributionPointsId;
    }

    /**
     * Reject the question.
     */
    public void reject()
    {
        this.valid = false;
    }
}
