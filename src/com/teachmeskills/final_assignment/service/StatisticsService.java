package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.consts.PathStatisticsFile;
import com.teachmeskills.final_assignment.consts.Regexp;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.teachmeskills.final_assignment.service.CurrencyConversionService.currencyConversion;

public class StatisticsService implements PathStatisticsFile {

    public static void ValidatorStatistics(List<String> value, String folderName) {
        switch (folderName) {
            case "checks": {
                writeTotalTurnoverOnChecks(value);
                break;
            }
            case "orders": {
                writeTotalTurnoverOnOrders(value);
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
                    }
                } else if (line.contains("GBR")) {
                    if (matcher.find()) {
                        totalInvoices += currencyConversion("GBR", matcher.group());
                    }
                } else {
                    if (matcher.find()) {
                        totalInvoices += Double.parseDouble(matcher.group());
                    }
                }
            }
            writer.write("The total turnover for all invoices is " + String.format("%.2f", totalInvoices) + "$" + " in 2023\n");
        } catch (IOException e) {
            System.out.println("Writer error");
        }
    }

    private static void writeTotalTurnoverOnChecks(List<String> amountLines) {
        double totalChecks = 0.0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TOTAL_TURNOVER_CHECKS))) {
            for (String line : amountLines) {
                Pattern pattern = Pattern.compile(Regexp.SUM_REGEXP);
                Matcher matcher = pattern.matcher(line);
                if (line.contains("EURO")) {
                    if (matcher.find()) {
                        totalChecks += currencyConversion("EURO", matcher.group());
                    }
                } else if (line.contains("GBR")) {
                    if (matcher.find()) {
                        totalChecks += currencyConversion("GBR", matcher.group());
                    }
                } else {
                    if (matcher.find()) {
                        totalChecks += Double.parseDouble(matcher.group());
                    }
                }
            }
            writer.write("The total turnover for all checks is " + String.format("%.2f", totalChecks) + "$" + " in 2023\n");
        } catch (IOException e) {
            System.out.println("Writer error");
        }
    }

    private static void writeTotalTurnoverOnOrders(List<String> amountLines) {
        double totalOrders = 0.0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TOTAL_TURNOVER_ORDERS))) {
            for (String line : amountLines) {
                Pattern pattern = Pattern.compile(Regexp.SUM_REGEXP);
                Matcher matcher = pattern.matcher(line);
                if (line.contains("EURO")) {
                    if (matcher.find()) {
                        totalOrders += currencyConversion("EURO", matcher.group());
                    }
                } else if (line.contains("GBR")) {
                    if (matcher.find()) {
                        totalOrders += currencyConversion("GBR", matcher.group());
                    }
                } else {
                    if (matcher.find()) {
                        totalOrders += Double.parseDouble(matcher.group());
                    }
                }
            }
            writer.write("The total turnover for all orders is " + String.format("%.2f", totalOrders) + "$" + " in 2023\n");
        } catch (IOException e) {
            System.out.println("Writer error");
        }
    }
    public static void writeTotalTurnoverOnAllFiles() {
        double totalAmount = 0.0;
        String line;
        Pattern pattern = Pattern.compile(Regexp.SUM_REGEXP);
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_TOTAL_TURNOVER_INVOICES))) {
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()){
                    totalAmount += Double.parseDouble(matcher.group().replace(",","."));
                }
            }
        }
        catch (IOException e) {

        }
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_TOTAL_TURNOVER_CHECKS))) {
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()){
                    totalAmount += Double.parseDouble(matcher.group().replace(",","."));
                }
            }
        }
        catch (IOException e) {

        }
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_TOTAL_TURNOVER_ORDERS))) {
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()){
                    totalAmount += Double.parseDouble(matcher.group().replace(",","."));
                }
            }
        }
        catch (IOException e) {

        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TOTAL_TURNOVER_YEAR))) {
            writer.write("The total turnover of orders, invoices and checks is " + String.format("%.2f", totalAmount) + "$" + " in 2023\n");
        }
        catch (IOException e) {

        }
    }
}
