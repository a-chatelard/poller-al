package com.esgi.questions.domain.questionPosee;

import com.esgi.questions.domain.common.exceptions.DomainException.DomainException;

import javax.persistence.*;
import java.util.*;

/**
 * Question posée à un utilisateur
 */
@Entity
public class QuestionPosee {
    /**
     * Identifiant de la question posée.
     */
    @GeneratedValue
    @Id
    private Long id;
    /**
     * Identifiant de la question relative.
     */
    private Long questionId;
    /**
     * Identfiant de l'utilisateur à qui la question a été posée.
     */
    private Long utilisateurId;
    /**
     * Réponses données à cette question posée.
     * Une question peut être posée plusieurs fois à un utilisateur.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "questionPosee")
    private List<ReponseUtilisateur> reponses = new ArrayList<>();

    protected QuestionPosee() {}

    public QuestionPosee(long questionId, long utilisateurId) throws DomainException {
        this.questionId = questionId;
        this.utilisateurId = utilisateurId;
        this.addPendingReponse();
    }

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

    /**
     * Ajoute une réponse en attente à la question posée.
     * @throws DomainException Exception levée si cette question posée a déjà une réponse en attente.
     */
    public void addPendingReponse() throws DomainException {
        var anyPendingResponse = this.reponses.stream().anyMatch(r -> r.getReponse() == null);
        if (anyPendingResponse) {
            throw new DomainException("Cette question a déjà été posée à l'utilisateur et est en attente de réponse.");
        }
        this.reponses.add(new ReponseUtilisateur());
    }

    /**
     * Répond à la question posée
     * @param reponseUtilisateur Réponse donnée par l'utilisateur.
     * @param pointsObtenus Nombre de points obtenus suite à la réponse de l'utilisateur.
     * @throws DomainException Exception levée si cette question posée n'a pas de réponse en attente.
     */
    public void answer(Boolean reponseUtilisateur, Integer pointsObtenus) throws DomainException {
        var pendingResponse = this.reponses.stream().filter(r -> r.getReponse() == null).findFirst();

        if (pendingResponse.isEmpty()) {
            throw new DomainException("Cette question n'a pas de réponse en attente.");
        }

        pendingResponse.get().answer(reponseUtilisateur, pointsObtenus);
    }

    /**
     * Récupère le dernier nombre de points obtenus suite à une réponse à cette question.
     * @return Nombre représentant le dernier nombre de points obtenus suite à une réponse à cette question.
     * Si cette question n'a pas encore été repondue, retourne un Optional Empty.
     */
    public Optional<Integer> getLastPointsObtenus() {
        this.reponses.sort(Comparator.comparing(ReponseUtilisateur::getPointsObtenus));
        var lastReponse = this.reponses.stream().findFirst();
        return lastReponse.map(ReponseUtilisateur::getPointsObtenus);
    }
}
