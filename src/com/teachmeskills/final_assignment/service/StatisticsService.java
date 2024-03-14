package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.consts.Regexp;
import com.teachmeskills.final_assignment.logger.Logger;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.teachmeskills.final_assignment.consts.PathStatisticsFile.*;
import static com.teachmeskills.final_assignment.service.CurrencyConversionService.convertCurrency;

public class StatisticsService {
    /**
     *The method calculateTurnover accepts as input the collection amountLines from the method
     * checkValidFiles in which the monthly turnover is written, converts to dollars, calculates the total
     * turnover for each type of document and statistics are recorded in the corresponding file.
     */
    static void calculateTurnover(List<String> amountLines, String path) {
        Logger.logInfo(new Date(),"start calculating turnover - " + "\"calculateTurnover\"");
        double totalAmount = 0.0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String line : amountLines) {
                Pattern pattern = Pattern.compile(Regexp.SUM_REGEXP);
                Matcher matcher = pattern.matcher(line);
                if (line.contains("EURO")) {
                    if (matcher.find()) {
                        totalAmount += convertCurrency("EURO", matcher.group());
                    }
                } else if (line.contains("GBR")) {
                    if (matcher.find()) {
                        totalAmount += convertCurrency("GBR", matcher.group());
                    }
                } else {
                    if (matcher.find()) {
                        totalAmount += Double.parseDouble(matcher.group());
                    }
                }
            }
            writer.write("The total turnover is " + String.format("%.2f", totalAmount) + "$" + " in 2023\n");
        } catch (IOException e) {
            System.out.println("Неудалось найти путь к файлу");
            Logger.logError(new Date(),"Неудалось найти путь к файлу",e);
        }
        Logger.logInfo(new Date(),"end calculating turnover - " + "\"calculateTurnover\"");
    }

    /**
     * The method calculateTotalTurnoverOnAllFiles reads information from files with final statistics for each
     * type of document, summarizes and records in a file the total turnover for the year for all documents.
     */
    public static void calculateTotalTurnoverOnAllFiles() {
        Logger.logInfo(new Date(),"started calculating the total turnover for all files - " + "\"calculateTotalTurnoverOnAllFiles\"");
        double totalAmount = 0.0;
        String line;
        Pattern pattern = Pattern.compile(Regexp.SUM_REGEXP);

        List<String> path = Arrays.asList(
                PATH_TOTAL_TURNOVER_CHECKS,
                PATH_TOTAL_TURNOVER_INVOICES,
                PATH_TOTAL_TURNOVER_ORDERS
        );

        for (String pathTotal : path) {
            try (BufferedReader reader = new BufferedReader(new FileReader(pathTotal))) {
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        totalAmount += Double.parseDouble(matcher.group().replace(",", "."));
                    }
                }
            } catch (IOException e) {
                Logger.logError(new Date(),"Неудалось найти путь к файлу",e);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TOTAL_TURNOVER_YEAR))) {
            writer.write("The total turnover of orders, invoices and checks is " + String.format("%.2f", totalAmount) + "$" + " in 2023\n");
        } catch (IOException e) {
            Logger.logError(new Date(),"Неудалось найти путь к файлу",e);
        }
        Logger.logInfo(new Date(),"end calculating the total turnover for all files - " + "\"calculateTotalTurnoverOnAllFiles\"");
    }
}
