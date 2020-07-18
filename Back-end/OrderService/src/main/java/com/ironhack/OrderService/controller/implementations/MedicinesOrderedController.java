package com.ironhack.OrderService.controller.implementations;

import com.ironhack.OrderService.controller.interfaces.IMedicinesOrderedController;
import com.ironhack.OrderService.model.MedicinesOrdered;
import com.ironhack.OrderService.service.MedicinesOrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MedicinesOrderedController implements IMedicinesOrderedController {
    @Autowired
    private MedicinesOrderedService medicinesOrderedService;

    @GetMapping("/medicines-ordered")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicinesOrdered> findAll() {
        return medicinesOrderedService.findAll();
    }

    @GetMapping("/medicines-ordered/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MedicinesOrdered findById(@PathVariable Long id) {
        return medicinesOrderedService.findById(id);
    }

    @GetMapping("/medicines-ordered/order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicinesOrdered> findByOrderId(@PathVariable Long id) {
        return medicinesOrderedService.findByOrderId(id);
    }

    @PostMapping("/medicines-ordered")
    @ResponseStatus(HttpStatus.CREATED)
    public List<MedicinesOrdered> create(@RequestBody @Valid List<MedicinesOrdered> medicines) {
        return medicinesOrderedService.storeMedicines(medicines);
    }
}
