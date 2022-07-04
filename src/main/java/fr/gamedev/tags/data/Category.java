package fr.gamedev.tags.data;


import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Category {

    /**
     * The category id.
     */
    @GeneratedValue(strategy = SEQUENCE)
    @Id
    private long id;

    /**
     * The category's label.
     */
    private String label;

    @OneToMany(mappedBy = "category")
    private List<Tag> tags;

    public Category() {}

    public Category(String label) {
        this.label = label;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param newId the id to set
     */
    public void setId(
            final long newId) {
        this.id = newId;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param newLabel the label to set
     */
    public void setLabel(
            final String newLabel) {
        this.label = newLabel;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
