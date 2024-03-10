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

    public static List<File> getFolderNames(String path) {
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
        return fileList;
    }

    //этот метод рабочий, я проверила
    public static Set<File> sortOrderFiles(List<File> fileList){
        Set<File> orderFiles = new LinkedHashSet<>();
        for (File order : fileList){
            if (order.getName().toLowerCase().matches(Regexp.ORDER_FILE_NAME_REGEXP)){
                orderFiles.add(order);
            }else {
               //TODO записать невалидный файл в другой файл, хотя их вроде нужно переписать из папки дата в новый файл
            }
        }
        return orderFiles;
    }
    public static void compileStatisticsOnOrderFiles(Set<File> orderFiles){
        List<String> amountLines = new ArrayList<>();
        for (File order : orderFiles){
            try (BufferedReader reader = new BufferedReader(new FileReader(order))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.toLowerCase().contains("total")){
                        amountLines.add(line.replace(",",""));
                    }
                }
            }catch (IOException e) {
                //TODO тут что-то надо делать??
            }
        }
        for (String line : amountLines){
            Pattern pattern = Pattern.compile(Regexp.SUM_REGEXP);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                System.out.println(matcher.group(line));
            }
        }
    }
}


