package com.ironhack.PharmacyEdge.exceptions;

public class MedicineServiceDownException extends RuntimeException {
    public MedicineServiceDownException(String message) {
        super(message);
    }
}