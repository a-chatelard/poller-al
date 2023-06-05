package com.esgi.questions.domain.questionPosee;

import org.springframework.lang.Nullable;

import javax.persistence.*;

/**
 * Réponse utilisateur à une question posée.
 * Une question peut être posée plusieurs à un utilisateur.
 */
@Entity
public class ReponseUtilisateur {
    /**
     * Identifiant de la réponse utilisateur.
     */
    @GeneratedValue
    @Id
    private Long id;
    /**
     * Lien de navigation vers la question posée concernée par cette réponse.
     */
    @ManyToOne
    @JoinColumn(name="question_posee_id", nullable = false)
    private QuestionPosee questionPosee;
    /**
     * Réponse donnée par l'utilisateur. Si la réponse est nulle, l'utilisateur n'a pas encore répondu.
     */
    @Nullable
    private Boolean reponse;
    /**
     * Nombre de points obtenus par l'utilisateur suite à sa réponse à la question posée.
     */
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

    /**
     * Définit la réponse donnée par l'utilisateur ainsi que les points obtenus.
     * @param reponse
     * @param pointsObtenus
     */
    public void answer(Boolean reponse, Integer pointsObtenus) {
        this.reponse = reponse;
        this.pointsObtenus = pointsObtenus;
    }
}
