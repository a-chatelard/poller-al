package com.esgi.questions.domain.questionPosee;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
public class ReponseUtilisateur {
    @GeneratedValue
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name="question_posee_id", nullable = false)
    private QuestionPosee questionPosee;
    @Nullable
    private Boolean reponse;
    @Nullable
    private Integer pointsObtenus;

    public ReponseUtilisateur() {}

    public Long getId() {
        return id;
    }
    @Nullable
    public Boolean getReponse() {
        return reponse;
    }
    @Nullable
    public Integer getPointsObtenus() {
        return pointsObtenus;
    }

    public void answer(Boolean reponse, Integer pointsObtenus) {
        this.reponse = reponse;
        this.pointsObtenus = pointsObtenus;
    }
}
