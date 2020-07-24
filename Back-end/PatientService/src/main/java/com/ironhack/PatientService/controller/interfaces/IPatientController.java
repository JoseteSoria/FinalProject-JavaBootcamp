package com.ironhack.PatientService.controller.interfaces;

import com.ironhack.PatientService.model.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientController {
    public List<Patient> findAll();

    public Patient findById(Integer id);

    public Optional<Patient> findPatientByName(String name);

    public Patient create(Patient patient);

    public void delete(Integer id);
}
