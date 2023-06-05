package com.esgi.questions.domain.questionPosee.aggregate;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

@Entity
public class QuestionPosee {


    @GeneratedValue(generator = "seq_gen_question")
    @GenericGenerator(
            name = "seq_gen_question",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_question"),
                    @Parameter(name = "initial_value", value = "0"), @Parameter(name = "increment_size", value = "1")
            })


    @Id
    private long id;
    /**
     * The question Id.
     */
    private long questionId;
    /**
     * The user Id.
     */
    private long utilisateurId;
    /**
     * The user answer.
     */
    private boolean reponseUtilisateur;

    public QuestionPosee(long questionId, long utilisateurId) {
        this.questionId = questionId;
        this.utilisateurId = utilisateurId;
    }

    /**
     * @return the id.
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set.
     */
    public void setId(long id) {
        this.id = id;
    }

    public long getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId the questionId to set.
     */public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getUtilisateurId() {
        return utilisateurId;
    }
    /**
     * @param utilisateurId the utilisateurId to set.
     */
    public void setUtilisateurId(long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public boolean getReponseUtilisateur() {
        return reponseUtilisateur;
    }
    /**
     * @param reponseUtilisateur the reponseUtilisateur to set.
     */
    public void setReponseUtilisateur(boolean reponseUtilisateur) {
        this.reponseUtilisateur = reponseUtilisateur;
    }

    /**
     * @param reponseUtilisateur
     * save the user answer.
     */
    public void answer(boolean reponseUtilisateur) {
        this.setReponseUtilisateur(reponseUtilisateur);

    }
}
