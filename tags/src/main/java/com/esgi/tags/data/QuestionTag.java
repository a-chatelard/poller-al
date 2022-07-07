package com.esgi.tags.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class QuestionTag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Tag tag;

    private Long questionId;

    public QuestionTag() {}

    public QuestionTag(Tag tag, Long questionId) {
        this.tag = tag;
        this.questionId = questionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionTag that = (QuestionTag) o;
        return tag.equals(that.tag) && questionId.equals(that.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, questionId);
    }
}
