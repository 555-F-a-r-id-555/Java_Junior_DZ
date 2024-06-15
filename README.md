# Java_Junior_DZ

###  Урок 1 Лямбды и Stream API

``` java
* 1. Найти самого молодого сотрудника
  */
  static Optional<Person> findMostYoungestPerson(List<Person> people) {
  // FIXME: ваша реализация здесь
  }

/**
* 2. Найти департамент, в котором работает сотрудник с самой большой зарплатой
  */
  static Optional<Department> findMostExpensiveDepartment(List<Person> people) {
  // FIXME: ваша реализация здесь
  }

/**
* 3. Сгруппировать сотрудников по департаментам
  */
  static Map<Department, List<Person>> groupByDepartment(List<Person> people) {
  // FIXME: ваша реализация здесь
  }

/**
* 4. Сгруппировать сотрудников по названиям департаментов
  */
  static Map<String, List<Person>> groupByDepartmentName(List<Person> people) {
  // FIXME: ваша реализация здесь
  }

/**
* 5. В каждом департаменте найти самого старшего сотрудника
  */
  static Map<String, Person> getDepartmentOldestPerson(List<Person> people) {
  // FIXME: ваша реализация здесь
  }

/**
* *6. Найти сотрудников с минимальными зарплатами в своем отделе
* (прим. можно реализовать в два запроса)
  */
  static List<Person> cheapPersonsInDepartment(List<Person> people) {
  // FIXME: ваша реализация здесь
  }
```

###  Урок 2 Reflection API


```java
    /**
     * В существующий класс ObjectCreator добавить поддержку аннотации RandomDate (по аналогии с Random):
     * 1. Аннотация должна обрабатываться только над полями типа java.util.Date
     * 2. Проверить, что min < max
     * 3. В поле, помеченной аннотацией, нужно вставлять рандомную дату,
     * UNIX-время которой находится в диапазоне [min, max) [5,6) => 5
     *
     * 4. *** Добавить поддержку для типов Instant, ...
     * 5. *** Добавить атрибут Zone и поддержку для типов LocalDate, LocalDateTime
     */

    /**
     * Примечание:
     * Unix-время - количество милисекунд, прошедших с 1 января 1970 года по UTC-0.
     */
```

