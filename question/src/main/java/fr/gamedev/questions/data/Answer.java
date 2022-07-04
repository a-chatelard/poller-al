package fr.gamedev.question.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
    @OneToOne
    private Question question;

    /**
     * The correct answer of the question.
     */
    private Boolean correctAnswer;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param newId the id to set
     */
    public void setId(final long newId) {
        this.id = newId;
    }

    /**
     * @return the question
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * @param newQuestion the question to set
     */
    public void setQuestion(final Question newQuestion) {
        this.question = newQuestion;
    }

    /**
     * @return the correctAnswer
     */
    public Boolean getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * @param newCorrectAnswer the correctAnswer to set
     */
    public void setCorrectAnswer(final Boolean newCorrectAnswer) {
        this.correctAnswer = newCorrectAnswer;
    }

}
