package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.encoding.DataEncoding;
import com.teachmeskills.final_assignment.session.Session;
import com.teachmeskills.final_assignment.storage.StorageMock;

public class AuthService {
    public static Session auth(String login, String password){
        StorageMock storage = new StorageMock();
        String loginFromStorage = storage.getLogin();
        String decodedLogin = DataEncoding.decode(loginFromStorage);
        String passwordFromStorage = storage.getPassword();
        String decodedPassword = DataEncoding.decode(passwordFromStorage);
        if (login.equalsIgnoreCase(decodedLogin) && password.equals(decodedPassword)){
            //TODO здесь логи
            System.out.println("Login and password verification was successful");
            return new Session();
        } else {
            System.out.println("The wrong username or password was entered");
            return null;
        }
    }
}
