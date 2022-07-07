package com.esgi.tags.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UserTag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Tag tag;

    private Long userId;

    public UserTag() {}

    public UserTag(Tag tag, Long userId) {
        this.tag = tag;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void seUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTag userTag = (UserTag) o;
        return tag.equals(userTag.tag) && userId.equals(userTag.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, userId);
    }
}
