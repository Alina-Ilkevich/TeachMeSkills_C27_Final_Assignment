package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.custom_exceptions.WrongAuthException;
import com.teachmeskills.final_assignment.encoding.DataEncoding;
import com.teachmeskills.final_assignment.logger.Logger;
import com.teachmeskills.final_assignment.session.Session;
import com.teachmeskills.final_assignment.storage.StorageMock;

import java.util.Date;

public class AuthService {
    public static Session auth(String login, String password) {
        StorageMock storage = new StorageMock();

        String loginFromStorage = storage.getLogin();
        String passwordFromStorage = storage.getPassword();

        String decodedLogin = DataEncoding.decode(loginFromStorage);
        String decodedPassword = DataEncoding.decode(passwordFromStorage);

        if (login.equalsIgnoreCase(decodedLogin) && password.equals(decodedPassword)) {
            System.out.println("Login and password verification was successful");
            Logger.logInfo(new Date(), "Login and password verification was successful");
            return new Session();
        } else {
            System.out.println("The wrong username or password was entered");
            Logger.logError(new Date(), "The wrong username or password was entered", new WrongAuthException());
            return null;
        }
    }
}
