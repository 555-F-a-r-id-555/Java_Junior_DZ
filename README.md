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

###  Урок 3 JDBC  (Java  Database  Connectivity)
```java
        /**
         * С помощью JDBC, выполнить следующие пункты:
         * 1. Создать таблицу Person (скопировать код с семниара)
         * 2. Создать таблицу Department (id bigint primary key, name varchar(128) not null)
         * 3. Добавить в таблицу Person поле department_id типа bigint (внешний ключ)
         * 4. Написать метод, который загружает Имя department по Идентификатору person
         * 5. * Написать метод, который загружает Map<String, String>, в которой маппинг person.name -> department.name
         *   Пример: [{"person #1", "department #1"}, {"person #2", "department #3}]
         * 6. ** Написать метод, который загружает Map<String, List<String>>, в которой маппинг department.name -> <person.name>
         *   Пример:
         *   [
         *     {"department #1", ["person #1", "person #2"]},
         *     {"department #2", ["person #3", "person #4"]}
         *   ]
         *
         *  7. *** Создать классы-обертки над таблицами, и в пунктах 4, 5, 6 возвращать объекты.
         */
```

###  Урок 4 Java Persistence API (JPA) - Hibernate
```java
/**
 * Используя hibernate, создать таблицы:
 * 1. Post (публикация) (id, title)
 * 2. PostComment (комментарий к публикации) (id, text, post_id)
 * <p>
 * Написать стандартные CRUD-методы: создание, загрузка, удаление.
 * <p>
 * Доп. задания:
 * 1. * В сущностях post и postComment добавить поля timestamp с датами.
 * 2. * Создать таблицу users(id, name) и в сущностях post и postComment добавить ссылку на юзера.
 * 3. * Реализовать методы:
 * 3.1 Загрузить все комментарии (post_comment)  публикации (post)
 * 3.2 Загрузить все публикации(post) по идентификатору юзера(user)
 * 3.3 ** Загрузить все комментарии(post_comment) по идентификатору юзера(user)
 * 3.4 **** По идентификатору юзера загрузить юзеров, под чьими публикациями он оставлял комменты.
 * // userId -> List<User>
 * 
 */
```
###  Урок 5 Клиент/Сервер своими руками
``` java
0. Осознать код, который мы написали на уроке.
   При появлении вопросов, пишем в общий чат в телеграме.
1. По аналогии с командой отправки сообщений, реализовать следующие команды:
   1.1 BroadcastMessageRequest - послать сообщение ВСЕМ пользователям (кроме себя)
   1.2 UsersRequest - получить список всех логинов, которые в данный момент есть в чате (в любом формате)
   1.3 DisconnectRequest - клиент оповещает сервер о том, что он отключился
   1.3.1 * Доп. задание: при отключении юзера, делать рассылку на остальных

Можно сделать только один пункт из 1.1-1.3.
```
