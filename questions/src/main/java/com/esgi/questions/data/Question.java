package com.esgi.questions.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author djer1
 */
@Entity
public class Question {

    /**
     * The question id.
     */
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
     * The question content.
     */
    private String content;

    /**
     * The question's tag.
     */
    @ManyToOne
    private String tag;

    public Question() { }

    public Question(final String newContent) {
        this.content = newContent;
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
     * @return the content.
     */
    public String getContent() {
        return content;
    }

    /**
     * @param newContent the content to set.
     */
    public void setContent(final String newContent) {
        this.content = newContent;
    }

    /**
     * @return the tag.
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param newTag the tag to set.
     */
    public void setTag(final String newTag) {
        this.tag = newTag;
    }
}
