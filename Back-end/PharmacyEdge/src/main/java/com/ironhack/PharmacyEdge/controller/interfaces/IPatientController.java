package com.ironhack.PharmacyEdge.controller.interfaces;

import com.ironhack.PharmacyEdge.model.patient.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface IPatientController {
    public List<Patient> findAll();

    public Patient findById(Integer id);

    public Optional<Patient> findPatientByName(String name);

    public Patient create(Patient patient);

    public void delete(Integer id);
}
