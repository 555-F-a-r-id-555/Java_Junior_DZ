package org.example.DZ4.DateCreation;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Random;

public class RandomAnnotationProcessor {
    public static void processAnnotationForData(Object obj) {
        Random random = new Random();
        Class<?> objClass = obj.getClass();
        for (Field field : objClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(RandomDate.class)) {
                RandomDate annotation = field.getAnnotation(RandomDate.class);
                long min = annotation.min();
                long max = annotation.max();

                field.setAccessible(true);
                if (min < max) {
                    try {
                        long rnd = min + (long) (random.nextDouble() * (max - min));
                        if (field.getType() == Timestamp.class) {
                            field.set(obj, new Timestamp(rnd));
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    throw new IllegalArgumentException("min должно быть меньше max. min = " + min + ", max = " + max);
                }
            }
        }
    }
}