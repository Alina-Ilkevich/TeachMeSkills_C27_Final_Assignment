package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.session.Session;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class FileProcessingService {
    public static void processFile(Session session, String pathToFolder) {
        if (session.isSessionAlive()) {
            System.out.println("live -> ");
            getFolderNames(pathToFolder);
        } else {
            System.out.println("ne live");
        }
    }

    public static void getFolderNames(String path) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (file.isDirectory()) {
                    System.out.println(file.getName() + "\t folder");
                    Arrays.stream(Objects.requireNonNull(file.listFiles()))
                            .filter(File::isFile)
                            .forEach(item1 -> System.out.println("\t files - " + item1.getName())
                            );
                }
            }
        }
    }
}


