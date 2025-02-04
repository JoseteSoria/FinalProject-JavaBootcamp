package com.ironhack.MedicineService.controller.implementations;

import com.ironhack.MedicineService.controller.interfaces.IMedicineController;
import com.ironhack.MedicineService.model.Medicine;
import com.ironhack.MedicineService.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MedicineController implements IMedicineController {
    @Autowired
    private MedicineService medicineService;

    @GetMapping("/medicines")
    @ResponseStatus(HttpStatus.OK)
    public List<Medicine> findAllMedicines() {
        return medicineService.findAll();
    }

    @GetMapping("/medicines/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Medicine findMedicineById(@PathVariable Long id) {
        return medicineService.findById(id);
    }

    @GetMapping("/medicines/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Medicine> findMedicineByName(@PathVariable(name = "name") String name) {
        return medicineService.findByName(name);
    }
}
