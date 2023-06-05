package com.esgi.questions.domain.question;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
    @GeneratedValue
    @Id
    private Long id;
    private Long ressourceId;
    private Long tagId;
    @Nullable
    private Long regleAttributionPointsId;
    private String libelle;
    private Boolean bonneReponse;
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

    public void accept(long regleAttributionPointsId)
    {
        this.valid = true;
        this.regleAttributionPointsId = regleAttributionPointsId;
    }

    public void reject()
    {
        this.valid = false;
    }
}
