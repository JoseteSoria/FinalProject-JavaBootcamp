package com.ironhack.MedicineService.controller.interfaces;

import com.ironhack.MedicineService.model.Medicine;

import java.util.List;
import java.util.Optional;

public interface IMedicineController {

    public List<Medicine> findAllMedicines();

    public Medicine findMedicineById(Long id);

    public Optional<Medicine> findMedicineByName(String name);
}
