package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.consts.ConstRate;
import com.teachmeskills.final_assignment.consts.CurrencyCode;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConversionService implements ConstRate, CurrencyCode {

    public static Double currencyConversion(String value, String amount) {

        Map<String, Double> map = new HashMap<>();

        map.put(CurrencyCode.EURO, ConstRate.EUR_TO_USD);
        map.put(CurrencyCode.GBR, ConstRate.GBR_TO_USD);

        System.out.println(value + " -> " + amount);
        System.out.println(map.get(value));

        double stat = Double.parseDouble(amount) / map.get(value);
        System.out.println("USD -> " + stat);
        return stat;
    }
}
