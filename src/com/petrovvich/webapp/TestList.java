package com.petrovvich.webapp;

import com.petrovvich.webapp.model.Resume;

import java.util.*;

public class TestList {

    private static Map<String, Resume> map = new HashMap<>();

    public static void main(String[] args) {
        String UUID_1 = "uuid1";
        Resume resume1 = new Resume(UUID_1);
        String UUID_2 = "uuid2";
        Resume resume2 = new Resume(UUID_2);
        String UUID_3 = "uuid3";
        Resume resume3 = new Resume(UUID_3);

        map.put(resume1.getUuid(), resume1);
        map.put(resume2.getUuid(), resume2);
        map.put(resume3.getUuid(), resume3);

        for (String key : map.keySet()) {
            System.out.println("Key: " + key);
        }

        String s = "123456789";

        StringBuilder sb = new StringBuilder(s);

        System.out.println(sb.reverse());

    }
}
