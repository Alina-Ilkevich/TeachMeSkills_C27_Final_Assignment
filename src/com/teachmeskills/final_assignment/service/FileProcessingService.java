package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.session.Session;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.teachmeskills.final_assignment.consts.Regexp.*;
import static com.teachmeskills.final_assignment.service.StatisticsService.calculateTurnover;

public class FileProcessingService {
    public static void processFile(Session session, String pathToFolder) {
        if (session.isSessionAlive()) {
            System.out.println("live -> ");
            checkValidFiles(searchFolderFiles(pathToFolder));
        } else {
            System.out.println("ne live");
        }
    }

    private static Map<String, List<File>> searchFolderFiles(String path) {
        FileFilter logFilefilter = file -> {
            if (file.getName().toLowerCase().matches(CHECK_FILE_NAME_REGEXP)) {
                return true;
            } else if (file.getName().toLowerCase().matches(INVOICE_FILE_NAME_REGEXP)) {
                return true;
            } else if (file.getName().toLowerCase().matches(ORDER_FILE_NAME_REGEXP)) {
                return true;
            } else {
//                Path source = Paths.get(file.getPath());
//                Path dirInvalidFiles = Paths.get(PATH_INVALID_FILES);
//                try {
//                    Files.move(source, dirInvalidFiles.resolve(source.getFileName()), ATOMIC_MOVE);
//                } catch (IOException ignored) {
//                }
                return false;
            }
        };
        File dir = new File(path);
        List<File> files = null;
        Map<String, List<File>> fileList = new HashMap<>();
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                files = Arrays.stream(Objects.requireNonNull(file.listFiles(logFilefilter)))
                        .filter(File::isFile)
                        .collect(Collectors.toList());
                fileList.put(file.getPath(), files);
            }
        }
        return fileList;
    }

    private static String GetStatisticsPathValidator(String pathTotalTurnover) {
        switch (pathTotalTurnover) {
            case "data\\invoices" -> {
                return "statistics/total_turnover_to_invoices.txt";
            }
            case "data\\orders" -> {
                return "statistics/total_turnover_of_orders.txt";
            }
            case "data\\checks" -> {
                return "statistics/total_turnover_checks.txt";
            }
        }
        return pathTotalTurnover;
    }

    private static void checkValidFiles(Map<String, List<File>> folder) {
        List<String> amountLines = new ArrayList<>();
        folder.forEach((key, value) -> {
            for (File file : value) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.toLowerCase().contains("total")) {
                            if (key.equals("data\\checks")) {
                                amountLines.add(line.replace(",", "."));
                            } else {
                                amountLines.add(line.replace(",", ""));
                            }
                        }
                    }
                } catch (IOException ignore) {
                }
            }
            calculateTurnover(amountLines, GetStatisticsPathValidator(key));
            amountLines.clear();
        });
    }
}