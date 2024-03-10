package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.consts.PathToFolder;
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

public class OrderStatistics {
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

    //TODO закинуть результат расчетов в final_statistics
    public static double compileStatisticsOnOrderFiles(Set<File> orderFiles){
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
        double orderSum = 0.0;
        for (String line : amountLines){
            Pattern pattern = Pattern.compile(Regexp.SUM_REGEXP);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                orderSum += Double.parseDouble(matcher.group());
            }
        }
        System.out.println("The total turnover for all orders is " + orderSum);
        return orderSum;
    }
}
