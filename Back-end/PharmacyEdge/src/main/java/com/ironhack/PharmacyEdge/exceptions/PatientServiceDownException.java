package com.ironhack.PharmacyEdge.exceptions;

public class PatientServiceDownException extends RuntimeException {
    public PatientServiceDownException(String message) {
        super(message);
    }
}