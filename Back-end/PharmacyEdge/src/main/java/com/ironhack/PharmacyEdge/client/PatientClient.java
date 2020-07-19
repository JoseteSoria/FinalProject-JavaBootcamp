package com.ironhack.PharmacyEdge.client;

import com.ironhack.PharmacyEdge.model.patient.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@FeignClient(name = "patient-service")
public interface PatientClient {
    @GetMapping("/patients")
    public List<Patient> findAll();

    @GetMapping("/patients/{id}")
    public Patient findById(@PathVariable Integer id);

    @GetMapping("/patients/name/{name}")
    public Optional<Patient> findByUsername(@PathVariable(name = "name") String name);

    @PostMapping("/patients")
    public Patient create(@RequestBody @Valid Patient patient);

    @DeleteMapping("/patients/{id}")
    public void delete(@PathVariable Integer id);
}
