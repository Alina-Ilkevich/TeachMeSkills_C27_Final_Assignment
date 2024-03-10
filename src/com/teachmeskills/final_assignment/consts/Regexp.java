package com.teachmeskills.final_assignment.consts;

public interface Regexp {
    String CHECK_FILE_NAME_REGEXP = "^2023_electric_bill_[\\d]{2}.txt$";
    String INVOICE_FILE_NAME_REGEXP = "^invoice_[\\d]{2}_2023&";
    String ORDER_FILE_NAME_REGEXP = "^2023_order_[\\d]{2}.txt$";
    String SUM_REGEXP = "\\d+\\.\\d+";
    //TODO в ордерах некоторые файлы повторяются
    //TODO сделать нечувствительным к регистру в названии файлов
}
