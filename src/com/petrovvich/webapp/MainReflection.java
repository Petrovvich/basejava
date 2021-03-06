package com.petrovvich.webapp;

import com.petrovvich.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println();
        System.out.println(field.get(r));
        field.set(r, "new uuid");
        System.out.println();
        System.out.println(r);

        //Optional homework for HW4
        System.out.println(r.getClass().getMethod("toString").invoke(r));

    }
}
