package com.ironhack.PharmacyEdge.controller.implementations;

import com.ironhack.PharmacyEdge.controller.interfaces.IPatientController;
import com.ironhack.PharmacyEdge.model.patient.Patient;
import com.ironhack.PharmacyEdge.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController implements IPatientController {
    @Autowired
    PatientService patientService;

    @GetMapping("/patients")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/patients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient findById(@PathVariable Integer id) {
        return patientService.findById(id);
    }

    @GetMapping("/patients/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Patient> findPatientByName(@PathVariable(name = "name") String name) {
        return patientService.findPatientByName(name);
    }

    @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient create(@RequestBody @Valid Patient patient) {
        return patientService.create(patient);
    }

    @DeleteMapping("/patients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        patientService.delete(id);
    }
}
