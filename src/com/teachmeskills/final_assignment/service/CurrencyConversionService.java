package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.consts.ConstRate;
import com.teachmeskills.final_assignment.consts.CurrencyCode;

import java.util.HashMap;
import java.util.Map;

/**
 * The method convertCurrency is used to convert euros and pounds into dollars.
 */
public class CurrencyConversionService {

    public static Double convertCurrency(String value, String amount) {
        Map<String, Double> map = new HashMap<>();
        map.put(CurrencyCode.EURO, ConstRate.EUR_TO_USD);
        map.put(CurrencyCode.GBR, ConstRate.GBR_TO_USD);
        return Double.parseDouble(amount) / map.get(value);
    }
}
