package org.example.DZ4V2.Models;

import lombok.Setter;
import org.example.DZ4V2.DateCreation.RandomAnnotationProcessor;
import org.example.DZ4V2.DateCreation.RandomDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "post")
public class Post {

    private static Long count = 1L;
    private static final Long numberOfUsers = 6L;  // на единицу меньше
    @Setter
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Setter
    @Column(name = "post_date")
    @RandomDate(min = 1704067200000L, max = 1794978900000L)
    private Timestamp date;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostComment> comments;

    public Long getId() {
        return id;
    }

    public void setTitle(String title, Long titleId) {
        this.title = title + "#" + ThreadLocalRandom.current().nextLong(1, titleId);
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getDate() {
        return date;
    }

    public List<PostComment> getComments() {
        return comments;
    }

    public void setComments(List<PostComment> comments) {
        this.comments = comments;
    }

    public Post() {
        setId(count++);
        setTitle("Tile", numberOfUsers);
        RandomAnnotationProcessor.processAnnotationForData(this);
    }
}
