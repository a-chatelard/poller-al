package com.esgi.questions.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author djer1
 *
 */
@Entity
public class UserAnswer {
    /**
     * The user answer id.
     */
    @GeneratedValue(generator = "seq_gen_userAnswer")
    @GenericGenerator(
            name = "seq_gen_userAnswer",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_answerUser"),
                    @Parameter(name = "initial_value", value = "0"), @Parameter(name = "increment_size", value = "1")
            })
    @Id
    private long id;

    /**
     * The id of the user who answered.
     */
    private long userId;

    /**
     * The concerned answer.
     */
    @ManyToOne
    private Answer answer;

    /**
     * The points obtained. If null, the user didn't answer yet.
     */
    private Long points = null;

    public UserAnswer() { }

    public UserAnswer(final Answer newAnswer, final long newUserId) {
        this.answer = newLabel;
        this.userId = newUserId;
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
     * @return the user.
     */
    public User getUserId() {
        return userId;
    }

    /**
     * @param newUserId the userId to set.
     */
    public void setUserId(final long newUserId) {
        this.userId = newUserId;
    }

    /**
     * @return the points.
     */
    public long getPoints() {
        return points;
    }

    /**
     * @param newPoints the points to set.
     */
    public void setPoints(final long newPoints) {
        this.points = newPoints;
    }

    /**
     * @return the answer.
     */
    public Answer getAnswer() {
        return answer;
    }

    /**
     * @param newAnswer the answer to set.
     */
    public void setAnswer(final Answer newAnswer) {
        this.answer = newAnswer;
    }

}
