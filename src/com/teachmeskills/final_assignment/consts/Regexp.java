package com.teachmeskills.final_assignment.consts;

public interface Regexp {
    String ORDER_FILE_NAME_REGEXP = "^2023_Electric_Bill_[\\d]{2}.txt$";
    String INVOICE_FILE_NAME_REGEXP = "^invoice_[\\d]{2}_2023&";
    String CHECK_FILE_NAME_REGEXP = "^2023_Order_[\\d]{2}.txt$";
    //TODO в ордерах некоторые файлы повторяются
    //TODO сделать нечувствительным к регистру в названии файлов
}
