package org.example.DZ3.V1;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Person {

    @Getter
    private String personName;
    @Getter
    private int personAge;
    private boolean personActive;
    @Setter
    @Getter
    private long departmentId;

    List<String> names = List.of("Vasya", "Petya", "Sasha", "Dasha", "Lena", "Olya", "Vlad");

    public Person(long departmentId) {
        this.departmentId = departmentId;
        setPersonName(names);
        setPersonAge();
        setPersonActive();
    }

    private static <T> T getRandom(List<? extends T> items) {
        int index = ThreadLocalRandom.current().nextInt(0, items.size());
        return items.get(index);
    }

    public void setPersonName(List<String> names) {
        this.personName = getRandom(names);
    }

    public void setPersonAge() {
        this.personAge = ThreadLocalRandom.current().nextInt(18, 75);
    }

    public void setPersonActive() {
        this.personActive = ThreadLocalRandom.current().nextBoolean();
    }

    public boolean getPersonActive() {
        return personActive;
    }

}
