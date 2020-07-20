package com.ironhack.SellService.controller.implementations;

import com.ironhack.SellService.controller.interfaces.IMedicineSoldController;
import com.ironhack.SellService.model.MedicineSold;
import com.ironhack.SellService.service.MedicineSoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MedicineSoldController implements IMedicineSoldController {
    @Autowired
    private MedicineSoldService medicineSoldService;

    @GetMapping("/medicines-sold")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicineSold> findAllMedicinesSold() {
        return medicineSoldService.findAll();
    }

    @GetMapping("/medicines-sold/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MedicineSold findMedicineSoldById(@PathVariable Long id) {
        return medicineSoldService.findById(id);
    }

    @GetMapping("/medicines-sold/sales/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicineSold> findMedicineSoldBySalesId(@PathVariable Long id) {
        return medicineSoldService.findBySalesId(id);
    }

    @PostMapping("/medicines-sold")
    @ResponseStatus(HttpStatus.CREATED)
    public List<MedicineSold> createMedicineSold(@RequestBody @Valid List<MedicineSold> medicines) {
        return medicineSoldService.storeMedicinesSold(medicines);
    }
}
