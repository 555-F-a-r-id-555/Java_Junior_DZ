package org.example.Lec2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<?> car = Class.forName("Car");
        Constructor<?>[] constructor = car.getConstructors();

        Object bmw = constructor[0].newInstance("BMW");
        System.out.println(bmw);
    }
}
