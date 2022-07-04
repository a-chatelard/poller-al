package fr.gamedev.tags.data;

import javax.persistence.*;

@Entity
public class UserTag {

    public UserTag() {}

    public UserTag(long userId) {
        this.userId = userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private long userId;

    @ManyToOne(optional = false)
    private Tag tag;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
