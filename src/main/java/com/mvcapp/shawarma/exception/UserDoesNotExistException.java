package com.mvcapp.shawarma.exception;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException(String msg) {
        super(msg);
    }
}
