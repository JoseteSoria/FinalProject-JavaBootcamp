package com.ironhack.PharmacyEdge.exceptions;

public class OrderServiceDownException extends RuntimeException {
    public OrderServiceDownException(String message) {
        super(message);
    }
}