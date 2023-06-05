package com.esgi.questions.domain.question.aggregate;

import com.esgi.questions.domain.question.valueObject.RessourceConcernee;
import org.springframework.lang.Nullable;

public class Question {
    private long id;
    private boolean bonneReponse;
    private String libelle;
    @Nullable
    private long regleAttributionPointsId;
    private RessourceConcernee ressource;
    @Nullable
    private boolean valid;

    public Question(long ressourceId, long tagId, String libelle, boolean bonneReponse)
    {
        RessourceConcernee ressource = new RessourceConcernee(ressourceId, tagId);

        this.ressource = ressource;
        this.libelle = libelle;
        this.bonneReponse = bonneReponse;
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
