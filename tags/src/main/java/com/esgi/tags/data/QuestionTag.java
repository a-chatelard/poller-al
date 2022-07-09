package com.esgi.tags.data;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import java.util.Objects;

@Entity
public class QuestionTag {

    /**
     * The id of the question tag.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * The related tag.
     */
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Tag tag;

    /**
     * The related question ID.
     */
    private Long questionId;

    public QuestionTag() { }

    public QuestionTag(final Tag newTag, final Long newQestionId) {
        this.tag = newTag;
        this.questionId = newQestionId;
    }

    /**
     * @return the question tag ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the question tag ID.
     * @param newId the ID to set.
     */
    public void setId(final Long newId) {
        this.id = newId;
    }

    /**
     * @return the related tag.
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * Set the related tag.
     * @param newTag the related tag.
     */
    public void setTag(final Tag newTag) {
        this.tag = newTag;
    }

    /**
     * @return the related question ID.
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * Set the related question ID.
     * @param newQuestionId the related question ID.
     */
    public void setQuestionId(final Long newQuestionId) {
        this.questionId = newQuestionId;
    }

    /**
     * Return if this question tag is equals to provided object.
     * @param o the object to compare the question tag with.
     * @return true if the object is the same, false otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionTag that = (QuestionTag) o;
        return tag.equals(that.tag) && questionId.equals(that.questionId);
    }

    /**
     * Get the hashcode of the object.
     * @return the object's hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(tag, questionId);
    }
}
