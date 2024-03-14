package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.logger.Logger;
import com.teachmeskills.final_assignment.session.Session;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.teachmeskills.final_assignment.consts.PathStatisticsFile.*;
import static com.teachmeskills.final_assignment.consts.Regexp.*;
import static com.teachmeskills.final_assignment.service.StatisticsService.calculateTurnover;

public class FileProcessingService {
    /**
     *The method processFile is public method to verify the validity of the session.
     * If the session is valid, then the following 2 methods are called:
     * searchFolderFiles and checkValidFiles.
     */
    public static void processFile(Session session, String pathToFolder) {
        if (session.isSessionAlive()) {
            checkValidFiles(searchFolderFiles(pathToFolder));
            StatisticsService.calculateTotalTurnoverOnAllFiles();
        } else {
            Logger.logInfo(new Date(),"закончилось время session, повторите автризацию");
            System.out.println("закончилось время session, повторите автризацию");
        }
    }

    /**
     *The method searchFolderFiles is used to search in the source folder "data" for files with a name
     * suitable for the standards. The method writes all valid files to the collection fileList.
     * All invalid files are written to the separate folder "invalid_files"
     */
    private static Map<String, List<File>> searchFolderFiles(String path) {
        Logger.logInfo(new Date(),"started searching for folders with files - " + "\"searchFolderFiles\"");
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
        File dir = new File(path.replace(" ",""));
        List<File> files = null;
        Map<String, List<File>> fileList = new HashMap<>();
        try {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (file.isDirectory()) {
                    files = Arrays.stream(Objects.requireNonNull(file.listFiles(logFilefilter)))
                            .filter(File::isFile)
                            .collect(Collectors.toList());
                    fileList.put(file.getPath(), files);
                }
            }
        }catch (NullPointerException e){
            System.out.println("Путь к папке указан не верно");
            Logger.logError(new Date(),"Путь к папке указан не верно",e);
        }
        Logger.logInfo(new Date(),"end searching for folders with files - " + "\"searchFolderFiles\"");
        return fileList;
    }

    /**
     *The method getStatisticsPathValidator is used to assign a path for recording statistics
     * for each type of document.
     */
    private static String getStatisticsPathValidator(String pathTotalTurnover) {
        switch (pathTotalTurnover) {
            case "data\\invoices" -> {
                return PATH_TOTAL_TURNOVER_INVOICES;
            }
            case "data\\orders" -> {
                return PATH_TOTAL_TURNOVER_ORDERS;
            }
            case "data\\checks" -> {
                return PATH_TOTAL_TURNOVER_CHECKS;
            }
        }
        return pathTotalTurnover;
    }

    /**
     *The method checkValidFiles reads information from files, finds the line where the turnover for the
     * current month is written and writes this line to the collection amountLines. As a result of executing
     * this method, the methods are called the method calculateTurnover from another class
     * and "getStatisticsPathValidator" from the class "FileProcessingService".
     */
    private static void checkValidFiles(Map<String, List<File>> folder) {
        Logger.logInfo(new Date(),"start checking if the files are valid - " + "\"checkValidFiles\"");
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
                } catch (IOException ignore) { }
            }
            calculateTurnover(amountLines, getStatisticsPathValidator(key));
            amountLines.clear();
        });
        Logger.logInfo(new Date(),"end checking if the files are valid - " + "\"checkValidFiles\"");
    }
}