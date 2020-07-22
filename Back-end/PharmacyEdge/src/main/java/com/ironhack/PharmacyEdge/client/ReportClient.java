package com.ironhack.PharmacyEdge.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "report-service")
public interface ReportClient {

    @GetMapping("/reports/sales-users/{months}")
    public List<Object[]> findSalesByUserInMonthsPeriod(@PathVariable(name = "months") Integer months);

    @GetMapping("/reports/purchases-patients/{months}")
    public List<Object[]> findPurchasesByPatientInMonthsPeriod(@PathVariable(name = "months") Integer months);

    @GetMapping("/reports/medicines-sold/{months}/{ranking}")
    public List<Object[]> findMoreMedicineSoldPeriodRanking(
            @PathVariable(name = "months") Integer months,
            @PathVariable(name = "ranking") Integer ranking);

    @GetMapping("/reports/medicines-revenue/{months}/{ranking}")
    public List<Object[]> findMoreMedicineRevenuePeriodRanking(
            @PathVariable(name = "months") Integer months,
            @PathVariable(name = "ranking") Integer ranking);
}
