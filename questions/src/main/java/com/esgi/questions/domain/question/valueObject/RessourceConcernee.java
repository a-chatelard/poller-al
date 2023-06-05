package com.esgi.questions.domain.question.valueObject;

public class RessourceConcernee {
    private long ressourceId;
    private long tagId;

    public RessourceConcernee(long ressourceId, long tagId)
    {
        this.ressourceId = ressourceId;
        this.tagId = tagId;
    }
}
