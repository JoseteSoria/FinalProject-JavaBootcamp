package com.ironhack.PharmacyEdge.exceptions;

public class SellServiceDownException extends RuntimeException {
    public SellServiceDownException(String message) {
        super(message);
    }
}