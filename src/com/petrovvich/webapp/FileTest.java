package com.petrovvich.webapp;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileTest {


    public static void getAllItems(File directory) {
        for (File directories : directory.listFiles()) {
            if (directories.isDirectory()) {
                try {
                    System.out.println(directories.getCanonicalPath());
                    getAllItems(directories);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        File file = new File("C:\\Projects\\basejava\\README.md");
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());
        try {
            System.out.println(file.getCanonicalFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.getFreeSpace()/1024/1024);
        System.out.println(file.getTotalSpace()/1024/1024);
        System.out.println(file.getUsableSpace()/1024/1024);
        System.out.println(file.canRead());
        System.out.println(file.canExecute());
        System.out.println(new Date(file.lastModified()));
        System.out.println(file.isDirectory());

        File directory = new File("C:\\Projects\\basejava");

        getAllItems(directory);
    }
}
