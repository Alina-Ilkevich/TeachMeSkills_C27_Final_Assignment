package com.teachmeskills.final_assignment.encoding;

import java.util.Base64;
import java.util.Random;
import java.util.stream.Collectors;

public class DataEncoding {
    public static String code(String input) {
        String encodedString = Base64.getEncoder().encodeToString(input.getBytes());
        String result = addSalt(encodedString);
        return result;
    }

    public static String decode(String input) {
        byte[] bytes = Base64.getDecoder().decode(input.substring(10));
        String result = new String(bytes);
        return result;
    }

    private static String addSalt(String input) {
        String symbols = "abcdefghijklmnopqrstuvwxyz0123456789";
        String salt = new Random().ints(10, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
        String result = salt + input;
        return result;
    }
}
