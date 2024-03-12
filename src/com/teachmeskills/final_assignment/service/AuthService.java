package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.custom_exceptions.WrongAuthException;
import com.teachmeskills.final_assignment.encoding.DataEncoding;
import com.teachmeskills.final_assignment.logger.Logger;
import com.teachmeskills.final_assignment.session.Session;
import com.teachmeskills.final_assignment.storage.StorageMock;

import java.util.Date;

/**
 * The method auth is used to authorize the user.
 * The method accepts the username and password that the user entered into the console.
 * The method takes the username and password in encoded form from the database,
 * decodes them and compares them with the username and password entered by the user.
 * When comparing logins from the database and that the user entered,
 * the letter case does not matter.
 */
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
