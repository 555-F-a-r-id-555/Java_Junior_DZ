package org.example.DZ2;
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
import org.example.DZ2.Lib.ObjectCreator;
import org.example.DZ2.Lib.Random;
import org.example.DZ2.Lib.RandomDate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

class AnnotationMain {

    public static void main(String[] args) {
        Person rndPerson = ObjectCreator.createObj(Person.class);
        System.out.println("age1 = " + rndPerson.age1);
        System.out.println("age2 = " + rndPerson.age2);
        System.out.println("age3 = " + rndPerson.age3);


//        String format = String.format("%tc", 100_000_000L);
//        System.out.println(format);

        System.out.println("--------------------------------");
        RndData rndDate = ObjectCreator.createObjForData(RndData.class);
        System.out.println("rndDate1 = " + String.format("%tc",rndDate.data1));
        System.out.println("rndDate2 = " + String.format("%tc",rndDate.data2));
        System.out.println("rndDate3 = " + String.format("%tc",rndDate.data3));
        System.out.println("rndInstant = " + rndDate.instant);

        System.out.println("rndLocalDateMoscow = " + rndDate.localDateMoscow);
        System.out.println("rndDateTimeMoscow = " + rndDate.dateTimeMoscow);
        System.out.println("rndDateTime1 = " + rndDate.dateTime2);


    }



    public static class Person {

        @Random // рандомное число в диапазоне [0, 100)
        private int age1;

        @Random(min = 50, max = 51) // рандомное число в диапазоне [50, 51) => 50
        private int age2;

        @Random
        private String age3;

    }

    public static class RndData {

        @RandomDate(min = 1704067200000L, max = 1704067900000L) //
        private long data1;

        @RandomDate
        private long data2;

        @RandomDate(min = 10_000L, max = 1_000_000L)
        private long data3;

//        @RandomDate(min = 10_000L, max = 10L)
//        private long data33; // IllegalArgumentException("min должно быть меньше max. min = " + min + ", max = " + max);

        @RandomDate(min = 1000, max = 10_000)
        private Instant instant;


        @RandomDate(min = 1704067200000L, max = 1794978900000L,zone = "Europe/Moscow")
        private LocalDate localDateMoscow;

        @RandomDate(min = 1704067200000L, max = 1794978900000L,zone = "Europe/Moscow")
        private LocalDateTime dateTimeMoscow;

        @RandomDate(min = 1704067200000L, max = 1794978900000L,zone = "Europe/Rome")
        private LocalDateTime dateTime2;


    }
}
