package fr.gamedev.tags.data;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    /**
     * The category's label.
     */
    @Id
    private String label;

    /**
     * Related tags.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Tag> tags;

    public Category() {}

    public Category(String label) {
        this.label = label;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the list of related tags
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Add a tag in this category
     * @param tag the tag to add to this category
     */
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }
}
