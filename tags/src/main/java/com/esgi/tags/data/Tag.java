package com.esgi.tags.data;


import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;


@Entity
public class Tag {

    /**
     * The tag's label.
     */
    @Id
    private String label;

    /**
     * The tag's category.
     */
    @ManyToOne(optional = false)
    private Category category;

    @OneToMany(mappedBy = "tag")
    private List<UserTag> usersTags;

    @OneToMany(mappedBy = "tag")
    private List<QuestionTag> questionsTags;

    public Tag() {}

    public Tag(String label) {
        this.label = label;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the category of the tag
     */
    public Category getCategory() {
        return category;
    }
    /**
     * @param newCategory the category to set
     */
    public void setCategory(final Category newCategory) {
        this.category = newCategory;
    }

    public List<UserTag> getUsersTags() {
        return usersTags;
    }
    public void addUserTag(UserTag userTag) {
        this.usersTags.add(userTag);
    }
    public void removeUserTag(UserTag userTag) {
        this.usersTags.remove(userTag);
    }

    public List<QuestionTag> getQuestionsTags() {
        return questionsTags;
    }
    public void addQuestionTag(QuestionTag questionTag) {
        this.questionsTags.add(questionTag);
    }
    public void removeQuestionTag(QuestionTag questionTag) {
        this.questionsTags.remove(questionTag);
    }
}
