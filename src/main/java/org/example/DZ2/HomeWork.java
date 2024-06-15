package org.example.DZ2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class HomeWork {

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

    // FIXME: Заставить аннотацию ставится только над полями
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface RandomDate2 {

        long min() default 1704067200000L; // 1 января 2024 года UTC0

        long max() default 1735689600000L; // 1 января 2025 года UTC0

    String zone() default "Moscow";

    }

    public static void main(String[] args) {
        long t = 1704067700000L;

        Date date = new Date(t);
        System.out.println(date);

        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        System.out.println(zoneId);

        String zone = "Europe/Moscow";

//        String zone2 = ZoneId.of(date.zone);

//        System.out.println(LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.of("+3")));
        System.out.println(LocalDateTime.ofInstant(date.toInstant(), zoneId));

    }

}
