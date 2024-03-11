package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.consts.PathStatisticsFile;
import com.teachmeskills.final_assignment.consts.Regexp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.teachmeskills.final_assignment.service.CurrencyConversionService.currencyConversion;

public class StatisticsService implements PathStatisticsFile {

    public static void ValidatorStatistics(List<String> value, String folderName) {
        switch (folderName) {
            case "checks": {

                break;
            }
            case "orders": {
                break;
            }
            case "invoices": {
                writeTotalTurnoverOnInvoices(value);
                break;
            }
        }
    }

    private static void writeTotalTurnoverOnInvoices(List<String> amountLines) {
        double totalInvoices = 0.0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TOTAL_TURNOVER_INVOICES))) {
            for (String line : amountLines) {
                Pattern pattern = Pattern.compile(Regexp.SUM_REGEXP);
                Matcher matcher = pattern.matcher(line);
                if (line.contains("EURO")) {
                    if (matcher.find()) {
                        totalInvoices += currencyConversion("EURO", matcher.group());
                        System.out.println(totalInvoices);
                        writer.write("invoices -> " + String.format("%.2f", totalInvoices) + "\n");
                    }
                } else if (line.contains("GBR")) {
                    if (matcher.find()) {
                        totalInvoices += currencyConversion("GBR", matcher.group());
                        System.out.println(totalInvoices);
                        writer.write("invoices -> " + String.format("%.2f", totalInvoices) + "\n");
                    }
                } else {
                    if (matcher.find()) {
                        totalInvoices += Double.parseDouble(matcher.group());
                        System.out.println(totalInvoices);
                        writer.write("invoices -> " + String.format("%.2f", totalInvoices) + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Writer error");
        }
    }
}
