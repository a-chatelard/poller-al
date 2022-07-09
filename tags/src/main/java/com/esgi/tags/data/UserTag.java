package com.esgi.tags.data;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import java.util.Objects;

@Entity
public class UserTag {

    /**
     * The user tag ID.
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
     * The related user ID.
     */
    private Long userId;

    public UserTag() { }

    public UserTag(final Tag newTag, final Long newUserId) {
        this.tag = newTag;
        this.userId = newUserId;
    }

    /**
     * @return the ID of the user tag.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the user tag ID.
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
     * @return the related user ID.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Set the related user ID.
     * @param newUserId the related user ID.
     */
    public void seUserId(final Long newUserId) {
        this.userId = newUserId;
    }

    /**
     * Return if this user tag is equals to provided object.
     * @param o the object to compare the user tag with.
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
        UserTag userTag = (UserTag) o;
        return tag.equals(userTag.tag) && userId.equals(userTag.userId);
    }

    /**
     * Get the hashcode of the object.
     * @return the object's hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(tag, userId);
    }
}
