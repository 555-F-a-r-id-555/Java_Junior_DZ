package org.example.DZ4.Model;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "users")
public class User {

    private static <T> T getRandom(List<? extends T> items) {
        int index = ThreadLocalRandom.current().nextInt(0, items.size());
        return items.get(index);
    }

    private static Long count = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @Setter
    @Column(name = "name")
    private String userName;

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public User() {
        this.setId(count++);
        List<String> names = List.of("Vasya", "Petya", "Sasha", "Dasha", "Lena", "Olya", "Vlad");
        this.setUserName(getRandom(names));
    }
}
