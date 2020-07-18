package com.ironhack.MedicineService.exceptions;

public class IllegalInputException extends RuntimeException{
    public IllegalInputException(String message) {
        super(message);
    }
}
