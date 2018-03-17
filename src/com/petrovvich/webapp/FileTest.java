package com.petrovvich.webapp;

import java.io.File;
import java.util.Objects;

public class FileTest {


    public static void getAllItems(File directory, String slip) {
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile()) {
                System.out.println(slip + file.getName());
            } else if (file.isDirectory()) {
                getAllItems(file, slip + "  ");
            }
        }
    }


    public static void main(String[] args) {
        File directory = new File("C:\\Projects\\basejava\\src\\com\\petrovvich\\webapp");
        String slip = "    ";

        getAllItems(directory, slip);
    }
}
