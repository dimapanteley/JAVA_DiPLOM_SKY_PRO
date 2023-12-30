package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "ads")
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    private int price;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;
    @OneToOne
    private ImageEntity image;
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<CommentEntity> comments;
}
