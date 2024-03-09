package com.teachmeskills.final_assignment.run;

import com.teachmeskills.final_assignment.consts.Regexp;
import com.teachmeskills.final_assignment.encoding.DataEncoding;
import com.teachmeskills.final_assignment.service.AuthService;
import com.teachmeskills.final_assignment.service.FileProcessingService;
import com.teachmeskills.final_assignment.session.Session;

public class Runner {
    public static void main(String[] args) {
        //AuthService.auth("qwerty","TeachMeSkills123");
        //FileProcessingService.selectOrderFiles();
        FileProcessingService.getFiles();
    }
}
