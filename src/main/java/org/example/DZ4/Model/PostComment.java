package org.example.DZ4.Model;

import org.example.DZ4.DateCreation.RandomAnnotationProcessor;
import org.example.DZ4.DateCreation.RandomDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "post_comment")
public class PostComment {
//    id, text, post_id, user_id, timestamp.

    private static final Long numberOfUsers = 6L;  // на единицу меньше
    private static Long count = 1L;
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "text")
    private String text;
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "comment_date")
    @RandomDate(min = 1704067200000L, max = 1794978900000L)
    private Timestamp date;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text, Long postId) {
        this.text = text + "#" + ThreadLocalRandom.current().nextLong(1, postId);
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = ThreadLocalRandom.current().nextLong(1, postId);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = ThreadLocalRandom.current().nextLong(1, userId);
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public PostComment() {
        setId(count++);
        setText("Text", numberOfUsers);
        setPostId(numberOfUsers);
        setUserId(numberOfUsers);
        RandomAnnotationProcessor.processAnnotationForData(this);

    }
}
