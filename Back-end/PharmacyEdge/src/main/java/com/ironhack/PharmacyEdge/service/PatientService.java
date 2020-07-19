package com.ironhack.PharmacyEdge.service;

import com.ironhack.PharmacyEdge.client.PatientClient;
import com.ironhack.PharmacyEdge.exceptions.PatientServiceDownException;
import com.ironhack.PharmacyEdge.model.patient.Patient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private static final Logger LOGGER = LogManager.getLogger(PatientService.class);

    @Autowired
    private PatientClient patientClient;

    @HystrixCommand(fallbackMethod = "errorFindAll")
    public List<Patient> findAll() {
        LOGGER.info("GET request to retrieve every patient");
        return patientClient.findAll();
    }

    public List<Patient> errorFindAll() {
        LOGGER.error("Controlled exception - fail in GET request to retrieve all the patients. ");
        throw new PatientServiceDownException("Patient Service Down. Method findAll. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindById")
    public Patient findById(Integer id) {
        LOGGER.info("GET request to retrieve patient with id " + id);
        return patientClient.findById(id);
    }

    public Patient errorFindById(Integer id) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve the patient with id " + id);
        throw new PatientServiceDownException("Patient Service Down. Method findById. ");
    }

    @HystrixCommand(fallbackMethod = "errorCreate", ignoreExceptions = {RuntimeException.class})
    public Patient create(Patient patient) {
        LOGGER.info("POST request to create a new patient");
        return patientClient.create(patient);
    }

    public Patient errorCreate(Patient patient) {
        LOGGER.error("Controlled exception - fail in POST request to create a new patient. ");
        throw new PatientServiceDownException("Patient Service Down. Method create. ");
    }

    @HystrixCommand(fallbackMethod = "errorDelete")
    public void delete(Integer id) {
        LOGGER.info("GET request to retrieve patient with id " + id);
        patientClient.delete(id);
    }

    public void errorDelete(Integer id) {
        LOGGER.error("Controlled exception - fail in DELETE request to remove a patient with id: " + id);
        throw new PatientServiceDownException("Patient Service Down. Method delete. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindPatientByName")
    public Optional<Patient> findPatientByName(String name) {
        LOGGER.info("GET request to retrieve patient with name: " + name);
        return patientClient.findPatientByName(name);
    }

    public Optional<Patient> errorFindPatientByName(String name) {
        LOGGER.error("Controlled exception - Fail in Authorization to find patient with name " + name);
        throw new PatientServiceDownException("Patient Service Down. Method findPatientByName. ");
    }

}
