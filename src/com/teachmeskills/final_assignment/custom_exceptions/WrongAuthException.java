package com.teachmeskills.final_assignment.custom_exceptions;

public class WrongAuthException extends Exception {
    public WrongAuthException(){}
    public WrongAuthException(String message){super(message);}
}
