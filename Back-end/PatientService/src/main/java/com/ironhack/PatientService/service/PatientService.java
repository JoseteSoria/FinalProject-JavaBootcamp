package com.ironhack.PatientService.service;

import com.ironhack.PatientService.exceptions.ResourceNotFoundException;
import com.ironhack.PatientService.model.Patient;
import com.ironhack.PatientService.repository.PatientRepository;
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
    private PatientRepository patientRepository;

    public List<Patient> findAll (){
        LOGGER.info("GET request to retrieve all patients");
        return patientRepository.findAll();
    }

    public Patient findById (Integer id){
        LOGGER.info("GET request to retrieve patient by id. Id: " + id);
        return patientRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Patient not found with that id")
        );
    }

    public Patient store (Patient patient){
        LOGGER.info("POST request to store a new patient.");
        return patientRepository.save(patient);
    }

    public void delete (Integer id){
        LOGGER.info("DELETE request to delete a patient by id. Id: " + id);
        Patient patient = findById(id);
        patientRepository.delete(patient);
    }

    public Optional<Patient> findByName (String name){
        LOGGER.info("GET request to retrieve patient by name. Name: " + name);
        return patientRepository.findByName(name);
    }


}
