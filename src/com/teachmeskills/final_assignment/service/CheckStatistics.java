package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.consts.Regexp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckStatistics {
    public static Set<File> sortCheckFiles(List<File> fileList){
        Set<File> checkFiles = new LinkedHashSet<>();
        for (File check : fileList){
            if (check.getName().toLowerCase().matches(Regexp.CHECK_FILE_NAME_REGEXP)){
                System.out.println(check);
                checkFiles.add(check);
            }else {
                //TODO записать невалидный файл в другой файл, хотя их вроде нужно переписать из папки дата в новый файл
            }
        }
        System.out.println(checkFiles);
        return checkFiles;
    }

    //TODO закинуть результат расчетов в final_statistics
    public static double compileStatisticsOnCheckFiles(Set<File> checkFiles){
        List<String> amountLines = new ArrayList<>();
        for (File check : checkFiles){
            try (BufferedReader reader = new BufferedReader(new FileReader(check))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.toLowerCase().contains("EURO")){
                        amountLines.add(line.replace(",",""));
                    }
                }
            }catch (IOException e) {
                //TODO тут что-то надо делать??
            }
        }
        double checkSum = 0.0;
        for (String line : amountLines){
            Pattern pattern = Pattern.compile(Regexp.SUM_REGEXP);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                checkSum += Double.parseDouble(matcher.group());
            }
        }
        System.out.println("The total turnover for all checks is " + checkSum);
        return checkSum;
    }
}
