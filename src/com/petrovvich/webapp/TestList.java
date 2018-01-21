package com.petrovvich.webapp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestList {

    private static List<String> testList = new ArrayList<>(10000);

    public static void main(String[] args) {
        testList.add("Fuck");
        testList.add("Do");
        testList.add("Among");
        System.out.println(testList.size());
        Iterator<String> iterator = testList.iterator();
        while (iterator.hasNext()) {
            String r = iterator.next();
            System.out.println(r);
        }
    }
}
