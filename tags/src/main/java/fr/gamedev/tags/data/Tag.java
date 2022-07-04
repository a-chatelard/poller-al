package fr.gamedev.tags.data;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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
}
