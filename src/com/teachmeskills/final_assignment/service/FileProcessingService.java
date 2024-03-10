package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.consts.Regexp;
import com.teachmeskills.final_assignment.session.Session;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

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
        List<File> fileList = new ArrayList<>();
        if (dir.isDirectory()) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (file.isDirectory()) {
                    System.out.println(file.getName() + "\t folder");
                    stream(Objects.requireNonNull(file.listFiles()))
                            .filter(File::isFile)
                            .forEach(item1 -> System.out.println("\t files - " + item1.getName())
                            );

                    //здесь начинается мое решение
                    fileList = Arrays.stream(Objects.requireNonNull(file.listFiles()))
                    .filter(File::isFile).collect(Collectors.toList());
                }
            }
        }
        System.out.println(fileList);
    }
}


