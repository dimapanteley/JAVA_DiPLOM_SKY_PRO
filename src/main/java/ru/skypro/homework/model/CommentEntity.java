package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    private long createdAt;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;
    @ManyToOne
    @JoinColumn(name = "ad_id")
    private AdEntity ad;
}
