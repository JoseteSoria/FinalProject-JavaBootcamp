package com.ironhack.PharmacyEdge.handler;


import com.ironhack.PharmacyEdge.exceptions.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(UserServiceDownException.class)
    public void handleUserServiceDownException(UserServiceDownException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_GONE, e.getMessage());
    }

    @ExceptionHandler(PatientServiceDownException.class)
    public void handlePatientServiceDownException(PatientServiceDownException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_GONE, e.getMessage());
    }

    @ExceptionHandler(MedicineServiceDownException.class)
    public void handleMedicineServiceDownException(MedicineServiceDownException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_GONE, e.getMessage());
    }

    @ExceptionHandler(OrderServiceDownException.class)
    public void handleOrderServiceDownException(OrderServiceDownException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_GONE, e.getMessage());
    }

    @ExceptionHandler(SellServiceDownException.class)
    public void handleSellServiceDownException(SellServiceDownException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_GONE, e.getMessage());
    }
}
