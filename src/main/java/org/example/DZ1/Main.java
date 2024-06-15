package org.example.DZ1;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Main {

    //        Черный: \u001B[30m
    //        Красный: \u001B[31m
    //        Зеленый: \u001B[32m
    //        Желтый: \u001B[33m
    //        Синий: \u001B[34m
    //        Пурпурный: \u001B[35m
    //        Голубой: \u001B[36m
    //        Белый: \u001B[37m
    // ANSI escape коды для цветов
    String resetColor = "\u001B[0m";  // Сброс цвета


    public Person person;
    public Department department;

    private static <T> T getRandom(List<? extends T> items) {
        int index = ThreadLocalRandom.current().nextInt(0, items.size());
        return items.get(index);
    }

    public static void main(String[] args) {

        List<String> departmentName = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            departmentName.add("Department #" + i);
        }

        List<Department> departments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Department department = new Department();
            department.setName(getRandom(departmentName));
            departments.add(department);
        }

        List<String> names = List.of("Vasya", "Petya", "Sasha", "Dasha", "Lena", "Olya", "Vlad");

        List<Person> persons = new ArrayList<Person>();

        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setName(getRandom(names));
            person.setAge(ThreadLocalRandom.current().nextInt(20, 50));
            person.setSalary(ThreadLocalRandom.current().nextInt(50_000, 100_000));
            person.setDepart(getRandom((departments)));

            persons.add(person);
        }
        System.out.print("\u001B[34m");
        for (Person person : persons) {

            System.out.println(person);
        }
        System.out.println("\u001B[32m");
        System.out.println("-----------------1.Найти самого молодого сотрудника--------------------------------------------------------");
        System.out.print("\u001B[33m");
        System.out.println(findMostYoungestPerson(persons).get());
        System.out.println("\u001B[32m");
        System.out.println("-----------------2.Найти департамент, в котором работает сотрудник с самой большой зарплатой---------------");
        System.out.print("\u001B[33m");
        System.out.println(findMostExpensiveDepartment(persons).get().getName());
        System.out.println("\u001B[32m");
        System.out.println("-----------------3.Сгруппировать сотрудников по департаментам----------------------------------------------");
        System.out.print("\u001B[33m");
//        System.out.println(groupByDepartment(persons));
        for (Map.Entry<Department, List<Person>> item : groupByDepartment(persons).entrySet()) {
            System.out.println(item.getKey() + ":" + item.getValue());
        }
        System.out.println("\u001B[32m");
        System.out.println("-----------------4.Сгруппировать сотрудников по названиям департаментов------------------------------------");
        System.out.print("\u001B[33m");
//        System.out.println(groupByDepartment(persons));
        for (Map.Entry<String, List<Person>> item : groupByDepartmentName(persons).entrySet()) {
            System.out.println(item.getKey() + ":" + item.getValue());
        }
        System.out.println("\u001B[32m");
        System.out.println("-----------------5.В каждом департаменте найти самого старшего сотрудника------------------------------------");
        System.out.print("\u001B[33m");
        for (Map.Entry<String, Person> item : getDepartmentOldestPerson(persons).entrySet()) {
            System.out.println(item.getKey() + ":" + item.getValue());
        }
        System.out.println("\u001B[32m");
        System.out.println("-----------------6.Найти сотрудников с минимальными зарплатами в своем отделе---------------------------------");
        System.out.print("\u001B[33m");
        List<Person> cheapPersons = cheapPersonsInDepartment(persons);
        for (Person person : cheapPersons) {
            System.out.println(person);
        }
        System.out.println("\u001B[32m");
        System.out.println("-----------------6.Найти сотрудников с минимальными зарплатами в своем отделе---------------------------------");
        System.out.print("\u001B[33m");
        List<Person> cheapPersons2 = cheapPersonsInDepartment2(persons);
        for (Person person : cheapPersons2) {
            System.out.println(person);
        }


    }

    /**
     * 1. Найти самого молодого сотрудника
     */
    static Optional<Person> findMostYoungestPerson(List<Person> people) {
        return people.stream()
//                .min((x, y) -> Integer.compare(x.getAge(), (y.getAge())));
                .min(Comparator.comparingInt(Person::getAge));

    }

    /**
     * 2.Найти департамент, в котором работает сотрудник с самой большой зарплатой
     */
    static Optional<Department> findMostExpensiveDepartment(List<Person> people) {
        return people.stream()
//                .max((x, y) -> Double.compare(x.getSalary(), (y.getSalary())))
                .max(Comparator.comparingDouble(Person::getSalary))
                .map(Person::getDepart);
    }

    /**
     * 3. Сгруппировать сотрудников по департаментам
     */
    static Map<Department, List<Person>> groupByDepartment(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(Person::getDepart));
    }

    /**
     * 4.Сгруппировать сотрудников по названиям департаментов
     */
    static Map<String, List<Person>> groupByDepartmentName(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(x -> x.getDepart().getName()));
    }

    /**
     * 5.В каждом департаменте найти самого старшего сотрудника
     */
    static Map<String, Person> getDepartmentOldestPerson(List<Person> people) {
        return people.stream()
                .collect(Collectors.toMap(
                        p -> p.getDepart().getName(),
                        p -> p,
                        (x, y) -> {
                            if (x.getAge() > y.getAge()) return x;
                            else return y;
                        }
                ));
    }

    /**
     * *6.Найти сотрудников с минимальными зарплатами в своем отделе
     * (прим. можно реализовать в два запроса)
     */
    static List<Person> cheapPersonsInDepartment(List<Person> people) {
        Map<String, List<Person>> groupedByDepartment = people.stream()
                .collect(Collectors.groupingBy(person -> person.getDepart().getName()));

        List<Person> result = new ArrayList<>();
        for (Map.Entry<String, List<Person>> entry : groupedByDepartment.entrySet()) {
            OptionalDouble minSalary = entry.getValue().stream()
                    .mapToDouble(Person::getSalary)
                    .min();

            if (minSalary.isPresent()) {
                double min = minSalary.getAsDouble();
                entry.getValue().stream()
                        .filter(person -> person.getSalary() == min)
                        .forEach(result::add);
            }
        }

        return result;
    }

    static List<Person> cheapPersonsInDepartment2(List<Person> people) {

        Map<String, Optional<Person>> minSalaryInDepartment = people.stream()
                .collect(Collectors.groupingBy(
                        person -> person.getDepart().getName(),
                        Collectors.minBy(Comparator.comparingDouble(Person::getSalary))
                ));


        return minSalaryInDepartment.values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

}
