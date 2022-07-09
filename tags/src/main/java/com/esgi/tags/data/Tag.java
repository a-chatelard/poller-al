package com.esgi.tags.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
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

    /**
     * The related user tags.
     */
    @OneToMany(mappedBy = "tag")
    private List<UserTag> usersTags;

    /**
     * The related question tags.
     */
    @OneToMany(mappedBy = "tag")
    private List<QuestionTag> questionsTags;

    public Tag() { }

    public Tag(final String newLabel) {
        this.label = newLabel;
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

    /**
     * @return the related user tags.
     */
    public List<UserTag> getUsersTags() {
        return usersTags;
    }

    /**
     * Add a user tag.
     * @param newUserTag the user tag to add.
     */
    public void addUserTag(final UserTag newUserTag) {
        this.usersTags.add(newUserTag);
    }

    /**
     * Remove a user tag.
     * @param userTag the user tag to remove.
     */
    public void removeUserTag(final UserTag userTag) {
        this.usersTags.remove(userTag);
    }

    /**
     * @return the related question tags.
     */
    public List<QuestionTag> getQuestionsTags() {
        return questionsTags;
    }

    /**
     * Add a question tag.
     * @param newQuestionTag the question tag to add.
     */
    public void addQuestionTag(final QuestionTag newQuestionTag) {
        this.questionsTags.add(newQuestionTag);
    }

    /**
     * Remove a question tag.
     * @param questionTag the question tag to remove.
     */
    public void removeQuestionTag(final QuestionTag questionTag) {
        this.questionsTags.remove(questionTag);
    }
}
