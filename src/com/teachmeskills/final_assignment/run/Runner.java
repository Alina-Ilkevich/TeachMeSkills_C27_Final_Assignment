package com.teachmeskills.final_assignment.run;

import com.teachmeskills.final_assignment.consts.Regexp;
import com.teachmeskills.final_assignment.encoding.DataEncoding;
import com.teachmeskills.final_assignment.service.AuthService;

public class Runner {
    public static void main(String[] args) {
        //AuthService.auth("qwerty","TeachMeSkills123");
        String a = "2023_Electric_Bill_12.txt";
        String a1 = "2022_Electric_Bill_12.txt";
        String a2 = "2023_Electric_Bill_12.tx";
        if (a.matches(Regexp.ORDER_FILE_NAME_REGEXP)){
            System.out.println("ok");
        }else {
            System.out.println("not");
        }
        if (a1.matches(Regexp.ORDER_FILE_NAME_REGEXP)){
            System.out.println("ok");
        }else {
            System.out.println("not");
        }
        if (a2.matches(Regexp.ORDER_FILE_NAME_REGEXP)){
            System.out.println("ok");
        }else {
            System.out.println("not");
        }
    }
}
