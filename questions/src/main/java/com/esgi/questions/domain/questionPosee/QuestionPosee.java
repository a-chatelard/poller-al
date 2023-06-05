package com.esgi.questions.domain.questionPosee;

import com.esgi.questions.domain.common.exceptions.DomainException.DomainException;

import javax.persistence.*;
import java.util.*;

@Entity
public class QuestionPosee {
    @GeneratedValue
    @Id
    private Long id;
    private Long questionId;
    private Long utilisateurId;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "questionPosee")
    private List<ReponseUtilisateur> reponses = new ArrayList<>();

    protected QuestionPosee() {}

    public QuestionPosee(long questionId, long utilisateurId) throws DomainException {
        this.questionId = questionId;
        this.utilisateurId = utilisateurId;
        this.addPendingReponse();
    }

    /**
     * @return the id.
     */
    public Long getId() {
        return id;
    }
    public Long getQuestionId() {
        return questionId;
    }
    public Long getUtilisateurId() {
        return utilisateurId;
    }
    public List<ReponseUtilisateur> getReponses() {
        return Collections.unmodifiableList(this.reponses);
    }

    public void addPendingReponse() throws DomainException {
        var anyPendingResponse = this.reponses.stream().anyMatch(r -> r.getReponse() == null);
        if (anyPendingResponse) {
            throw new DomainException("Cette question a déjà été posée à l'utilisateur et est en attente de réponse.");
        }
        this.reponses.add(new ReponseUtilisateur());
    }

    /**
     * @param reponseUtilisateur
     * save the user answer.
     */
    public void answer(Boolean reponseUtilisateur, Integer pointsObtenus) throws DomainException {
        var pendingResponse = this.reponses.stream().filter(r -> r.getReponse() == null).findFirst();

        if (pendingResponse.isEmpty()) {
            throw new DomainException("Cette question n'a pas de réponse en attente.");
        }

        pendingResponse.get().answer(reponseUtilisateur, pointsObtenus);
    }

    public Optional<Integer> getLastPointsObtenus() {
        this.reponses.sort(Comparator.comparing(ReponseUtilisateur::getPointsObtenus));
        var lastReponse = this.reponses.stream().findFirst();
        return lastReponse.map(ReponseUtilisateur::getPointsObtenus);
    }
}
