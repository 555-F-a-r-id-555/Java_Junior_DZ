package org.example.DZ4.Model;

import lombok.Setter;
import org.example.DZ4.DateCreation.RandomAnnotationProcessor;
import org.example.DZ4.DateCreation.RandomDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
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

    @Column(name = "user_id")
    private Long userId;

    @Setter
    @Column(name = "post_date")
    @RandomDate(min = 1704067200000L, max = 1794978900000L)
    private Timestamp date;

    public Long getId() {
        return id;
    }

    public void setTitle(String title, Long titleId) {
        this.title = title + "#" + ThreadLocalRandom.current().nextLong(1, titleId);
    }

    public String getTitle() {
        return title;
    }

    public void setUserId(Long userId) {
        this.userId = ThreadLocalRandom.current().nextLong(1, userId);
    }

    public Long getUserId() {
        return userId;
    }

    public Timestamp getDate() {
        return date;
    }

    public Post() {
        setId(count++);
        setTitle("Tile", numberOfUsers);
        setUserId(numberOfUsers);
        RandomAnnotationProcessor.processAnnotationForData(this);
    }
}
