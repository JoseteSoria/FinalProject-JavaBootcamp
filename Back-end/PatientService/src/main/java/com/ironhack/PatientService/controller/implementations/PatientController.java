package com.ironhack.PatientService.controller.implementations;

import com.ironhack.PatientService.controller.interfaces.IPatientController;
import com.ironhack.PatientService.model.Patient;
import com.ironhack.PatientService.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController implements IPatientController {
    @Autowired
    private PatientService patientService;

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

    @GetMapping( "/patients/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Patient> findByUsername(@PathVariable(name = "name") String name) {
        return patientService.findByName(name);
    }

    @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient create (@RequestBody @Valid Patient patient){
        return patientService.store(patient);
    }

    @DeleteMapping("/patients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        patientService.delete(id);
    }
}
