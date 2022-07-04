package fr.gamedev.tags.data;

import javax.persistence.*;

@Entity
public class QuestionTag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private long questionId;

    @ManyToOne(optional = false)
    private Tag tag;
}
