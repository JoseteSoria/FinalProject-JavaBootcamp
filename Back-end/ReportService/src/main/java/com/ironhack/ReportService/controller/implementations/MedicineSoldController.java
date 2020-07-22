package com.ironhack.ReportService.controller.implementations;

import com.ironhack.ReportService.service.MedicineSoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MedicineSoldController {
    @Autowired
    private MedicineSoldService medicineSoldService;

    @GetMapping("/reports/medicines-sold/{months}/{ranking}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> findMoreMedicineSoldPeriodRanking(
            @PathVariable(name = "months") Integer months,
            @PathVariable(name = "ranking") Integer ranking) {
        return medicineSoldService.findMoreMedicineSoldPeriodRanking(months, ranking);
    }

    @GetMapping("/reports/medicines-revenue/{months}/{ranking}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> findMoreMedicineRevenuePeriodRanking(
            @PathVariable(name = "months") Integer months,
            @PathVariable(name = "ranking") Integer ranking) {
        return medicineSoldService.findMoreMedicineRevenuePeriodRanking(months, ranking);
    }
}
