package com.teachmeskills.final_assignment.run;

import com.teachmeskills.final_assignment.service.AuthService;
import com.teachmeskills.final_assignment.service.FileProcessingService;
import com.teachmeskills.final_assignment.service.StatisticsService;
import com.teachmeskills.final_assignment.session.Session;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        // login - "qwerty", password - "TeachMeSkills123", path - "data"

        int count = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите логин и пароль :");
            System.out.print("Логин - ");
            String login = scanner.nextLine();
            System.out.print("Пароль - ");
            String password = scanner.nextLine();
            Session session = AuthService.auth(login.replace(" ",""), password.replace(" ",""));
            if (session != null) {
                System.out.print("Введите путь к папке - ");
                String path = scanner.nextLine();
                FileProcessingService.processFile(session, path);
                return;
            } else {
                if (count == 1) return;
                System.out.println("\nУ вас осталась 1-на попытка входа");
                count++;
            }
        }
    }
}
