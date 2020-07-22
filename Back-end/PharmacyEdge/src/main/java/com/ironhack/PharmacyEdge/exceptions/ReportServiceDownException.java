package com.ironhack.PharmacyEdge.exceptions;

public class ReportServiceDownException extends RuntimeException {
    public ReportServiceDownException(String message) {
        super(message);
    }
}