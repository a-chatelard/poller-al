package fr.gamedev.tags.data;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Tag {

    public Tag() {}

    public Tag(String label) {
        this.label = label;
    }

    /**
     * The tag id.
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;

    /**
     * The users associated to the tag.
     */
    @OneToMany
    private Set<UserTag> usersTags = new HashSet<>();

    /**
     * The tag's category.
     */
    @ManyToOne
    private Category category;

    /**
     * The tag's label.
     */
    private String label;

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
     * @return the list of related user tags
     */
    public Set<UserTag> getUsersTags() {
        return usersTags;
    }

    public void setUsersTags(Set<UserTag> usersTags) {
        this.usersTags = usersTags;
    }

    public void addUserTag(UserTag userTag) {
        this.usersTags.add(userTag);
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
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param newLabel the label to set
     */
    public void setLabel(final String newLabel) {
        this.label = newLabel;
    }
}
