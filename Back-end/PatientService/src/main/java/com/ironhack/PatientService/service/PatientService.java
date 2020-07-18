package com.ironhack.PatientService.service;

import com.ironhack.PatientService.exceptions.ResourceNotFoundException;
import com.ironhack.PatientService.model.Patient;
import com.ironhack.PatientService.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> findAll (){
        return patientRepository.findAll();
    }

    public Patient findById (Integer id){
        return patientRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Patient not found with that id")
        );
    }

    public Patient store (Patient patient){
        return patientRepository.save(patient);
    }

    public void delete (Integer id){
        Patient patient = findById(id);
        patientRepository.delete(patient);
    }

    public Optional<Patient> findByName (String name){
        return patientRepository.findByName(name);
    }


}
