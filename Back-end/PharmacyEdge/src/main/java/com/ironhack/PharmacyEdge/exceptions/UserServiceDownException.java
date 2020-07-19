package com.ironhack.PharmacyEdge.exceptions;

public class UserServiceDownException extends RuntimeException {
    public UserServiceDownException(String message) {
        super(message);
    }
}
