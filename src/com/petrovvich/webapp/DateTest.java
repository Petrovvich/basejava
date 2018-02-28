package com.petrovvich.webapp;

import java.util.Date;

public class DateTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Date date = new Date();
        System.out.println(date);
        System.out.println(System.currentTimeMillis() - start);
    }
}
