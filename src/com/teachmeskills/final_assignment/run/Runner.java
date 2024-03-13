package com.teachmeskills.final_assignment.run;

import com.teachmeskills.final_assignment.service.AuthService;
import com.teachmeskills.final_assignment.service.FileProcessingService;
import com.teachmeskills.final_assignment.service.StatisticsService;
import com.teachmeskills.final_assignment.session.Session;

import java.util.Scanner;

import static com.teachmeskills.final_assignment.consts.PathToFolder.PATH_TO_FOLDER;

public class Runner {
    public static void main(String[] args) {

        // login "qwerty", password "TeachMeSkills123"

        //TODO чуть нижу рабочик код закомичен (21-38 строка)
        // для простоты проверки программы

        int count = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите логин и пароль :");
            System.out.print("Логин - ");
            String login = scanner.nextLine();
            System.out.print("Пароль - ");
            String password = scanner.nextLine();
            Session session = AuthService.auth(login, password);
            if (session != null) {
                FileProcessingService.processFile(session, PATH_TO_FOLDER);
                StatisticsService.writeTotalTurnoverOnAllFiles();
                return;
            } else {
                if (count == 1) return;
                System.out.println("\nУ вас осталась 1-на попытка входа");
                count++;
            }
        }

//        Session session = AuthService.auth("qwerty","TeachMeSkills123");
//        FileProcessingService.processFile(session,"data");


    }
}
