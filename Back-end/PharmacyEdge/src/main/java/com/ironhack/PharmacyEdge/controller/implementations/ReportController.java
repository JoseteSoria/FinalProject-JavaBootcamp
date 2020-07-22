package com.ironhack.PharmacyEdge.controller.implementations;

import com.ironhack.PharmacyEdge.controller.interfaces.IReportController;
import com.ironhack.PharmacyEdge.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ReportController implements IReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/reports/sales-users/{months}")
    public List<Object[]> findSalesByUserInMonthsPeriod(@PathVariable(name = "months") Integer months){
        return reportService.findSalesByUserInMonthsPeriod(months);
    }

    @GetMapping("/reports/purchases-patients/{months}")
    public List<Object[]> findPurchasesByPatientInMonthsPeriod(@PathVariable(name = "months") Integer months){
        return reportService.findPurchasesByPatientInMonthsPeriod(months);
    }

    @GetMapping("/reports/medicines-sold/{months}/{ranking}")
    public List<Object[]> findMoreMedicineSoldPeriodRanking(
            @PathVariable(name = "months") Integer months,
            @PathVariable(name = "ranking") Integer ranking){
        return reportService.findMoreMedicineSoldPeriodRanking(months, ranking);
    }

    @GetMapping("/reports/medicines-revenue/{months}/{ranking}")
    public List<Object[]> findMoreMedicineRevenuePeriodRanking(
            @PathVariable(name = "months") Integer months,
            @PathVariable(name = "ranking") Integer ranking){
        return reportService.findMoreMedicineRevenuePeriodRanking(months, ranking);
    }
}
