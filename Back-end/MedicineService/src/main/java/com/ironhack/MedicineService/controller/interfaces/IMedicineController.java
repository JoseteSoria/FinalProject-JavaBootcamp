package com.ironhack.MedicineService.controller.interfaces;

import com.ironhack.MedicineService.model.Medicine;

import java.util.List;
import java.util.Optional;

public interface IMedicineController {

    public List<Medicine> findAll();

    public Medicine findById(Long id);

    public Optional<Medicine> findByUsername(String name);
}
