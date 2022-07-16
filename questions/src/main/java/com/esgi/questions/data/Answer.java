package com.esgi.questions.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.List;

/**
 * @author djer1
 */
@Entity
public class Answer {
    /**
     * The answer id.
     */
    @GeneratedValue(generator = "seq_gen_answer")
    @GenericGenerator(
            name = "seq_gen_answer",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_answer"),
                    @Parameter(name = "initial_value", value = "0"), @Parameter(name = "increment_size", value = "1")
            })
    @Id
    private long id;

    /**
     * The question concerned by the answer.
     */
    @JsonIgnore
    @OneToOne(mappedBy = "answer")
    private Question question;

    /**
     * The correct answer of the question.
     */
    private Boolean correctAnswer;

    /**
     * The related user answers.
     */
    @OneToMany(mappedBy = "answer", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<UserAnswer> userAnswers;

    public Answer() { }

    public Answer(final Question newQuestion, final Boolean newCorrectAnswer) {
        this.question = newQuestion;
        this.correctAnswer = newCorrectAnswer;
    }

    /**
     * @return the id.
     */
    public long getId() {
        return id;
    }

    /**
     * @param newId the id to set.
     */
    public void setId(final long newId) {
        this.id = newId;
    }

    /**
     * @return the question.
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * @param newQuestion the question to set.
     */
    public void setQuestion(final Question newQuestion) {
        this.question = newQuestion;
    }

    /**
     * @return the correctAnswer.
     */
    public Boolean getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * @param newCorrectAnswer the correctAnswer to set.
     */
    public void setCorrectAnswer(final Boolean newCorrectAnswer) {
        this.correctAnswer = newCorrectAnswer;
    }

    /**
     * @return the user answers related to this answer.
     */
    public List<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    /**
     * Set the user answers.
     * @param newUserAnswers the new user answers.
     */
    public void setUserAnswers(final List<UserAnswer> newUserAnswers) {
        this.userAnswers = newUserAnswers;
    }
}
