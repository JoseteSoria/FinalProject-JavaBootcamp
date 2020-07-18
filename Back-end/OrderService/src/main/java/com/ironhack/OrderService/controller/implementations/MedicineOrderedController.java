package com.ironhack.OrderService.controller.implementations;

import com.ironhack.OrderService.controller.interfaces.IMedicineOrderedController;
import com.ironhack.OrderService.model.MedicineOrdered;
import com.ironhack.OrderService.service.MedicineOrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MedicineOrderedController implements IMedicineOrderedController {
    @Autowired
    private MedicineOrderedService medicineOrderedService;

    @GetMapping("/medicines-ordered")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicineOrdered> findAll() {
        return medicineOrderedService.findAll();
    }

    @GetMapping("/medicines-ordered/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MedicineOrdered findById(@PathVariable Long id) {
        return medicineOrderedService.findById(id);
    }

    @GetMapping("/medicines-ordered/order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicineOrdered> findByOrderId(@PathVariable Long id) {
        return medicineOrderedService.findByOrderId(id);
    }

    @PostMapping("/medicines-ordered")
    @ResponseStatus(HttpStatus.CREATED)
    public List<MedicineOrdered> create(@RequestBody @Valid List<MedicineOrdered> medicines) {
        return medicineOrderedService.storeMedicines(medicines);
    }
}
