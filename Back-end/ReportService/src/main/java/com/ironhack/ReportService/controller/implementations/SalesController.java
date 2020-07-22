package com.ironhack.ReportService.controller.implementations;

import com.ironhack.ReportService.controller.interfaces.ISalesController;
import com.ironhack.ReportService.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SalesController implements ISalesController {
    @Autowired
    private SalesService salesService;

    @GetMapping("/reports/sales-users/{months}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> findSalesByUserInMonthsPeriod(@PathVariable(name = "months") Integer months) {
        return salesService.findSalesByUserInMonthsPeriod(months);
    }

    @GetMapping("/reports/purchases-patients/{months}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> findPurchasesByPatientInMonthsPeriod(@PathVariable(name = "months") Integer months) {
        return salesService.findPurchasesByPatientInMonthsPeriod(months);
    }
}
