package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.consts.PathToFolder;
import com.teachmeskills.final_assignment.consts.Regexp;
import com.teachmeskills.final_assignment.session.Session;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.teachmeskills.final_assignment.service.StatisticsService.ValidatorStatistics;

public class FileProcessingService implements Regexp, PathToFolder {
    public static void processFile(Session session, String pathToFolder) {
        if (session.isSessionAlive()) {
            System.out.println("live -> ");
            Validator(Objects.requireNonNull(searchFolderFiles(pathToFolder)));
        } else {
            System.out.println("ne live");
        }
    }

    private static Map<String, List<File>> searchFolderFiles(String path) {
        File dir = new File(path);
        Map<String, List<File>> fileList = new HashMap<>();
        List<File> files;
        if (!dir.isDirectory()) return null;
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                files = Arrays.stream(Objects.requireNonNull(file.listFiles()))
                        .filter(File::isFile)
                        .collect(Collectors.toList());
                fileList.put(file.getName(), files);
            }
        }
        return fileList;
    }

    private static void Validator(Map<String, List<File>> folderName) {
        folderName.forEach((key, value) -> {
            switch (key) {
                case "checks": {
                    System.out.println("\n" + key + "\t folder");
                    checkValidFiles(key, value, CHECK_FILE_NAME_REGEXP);
                    break;
                }
                case "orders": {
                    System.out.println("\n" + key + "\t folder");
                    checkValidFiles(key, value, ORDER_FILE_NAME_REGEXP);
                    break;
                }
                case "invoices": {
                    System.out.println("\n" + key + "\t folder");
                    checkValidFiles(key, value, INVOICE_FILE_NAME_REGEXP);
                    break;
                }
            }
        });
    }

    private static void checkValidFiles(String folderName, List<File> fileList, String regex) {
        List<String> amountLines = new ArrayList<>();
        for (File order : fileList) {
            if (order.getName().toLowerCase().matches(regex)) {
                try (BufferedReader reader = new BufferedReader(new FileReader(order))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.toLowerCase().contains("total")) {
                            if (folderName.equals("checks")) {
                                amountLines.add(line.replace(",", "."));
                            } else {
                                amountLines.add(line.replace(",", ""));
                            }
                        }
                    }
                } catch (IOException ignore) {
                }
            } else {
                //TODO записать невалидный файл в другой файл, хотя их вроде нужно переписать из папки дата в новый файл
            }
        }

        amountLines.forEach(System.out::println);
        System.out.println("_----------------------------------_");
        ValidatorStatistics(amountLines, folderName);
    }
}


