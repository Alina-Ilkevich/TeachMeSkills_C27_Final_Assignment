package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.consts.PathStatisticsFile;
import com.teachmeskills.final_assignment.consts.Regexp;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.teachmeskills.final_assignment.service.CurrencyConversionService.convertCurrency;

public class StatisticsService implements PathStatisticsFile {
    static void writeTotalTurnoverOnInvoices(List<String> amountLines, String path) {
        double totalInvoices = 0.0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String line : amountLines) {
                Pattern pattern = Pattern.compile(Regexp.SUM_REGEXP);
                Matcher matcher = pattern.matcher(line);
                if (line.contains("EURO")) {
                    if (matcher.find()) {
                        totalInvoices += convertCurrency("EURO", matcher.group());
                    }
                } else if (line.contains("GBR")) {
                    if (matcher.find()) {
                        totalInvoices += convertCurrency("GBR", matcher.group());
                    }
                } else {
                    if (matcher.find()) {
                        totalInvoices += Double.parseDouble(matcher.group());
                    }
                }
            }
            writer.write("The total turnover is " + String.format("%.2f", totalInvoices) + "$" + " in 2023\n");
        } catch (IOException e) {
            System.out.println("Writer error");
        }
    }

    public static void writeTotalTurnoverOnAllFiles() {
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

            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TOTAL_TURNOVER_YEAR))) {
            writer.write("The total turnover of orders, invoices and checks is " + String.format("%.2f", totalAmount) + "$" + " in 2023\n");
        } catch (IOException e) {

        }
    }
}
