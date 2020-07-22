package com.ironhack.ReportService.controller.interfaces;

import java.util.List;

public interface ISalesController {

    public List<Object[]> findSalesByUserInMonthsPeriod(Integer months);

    public List<Object[]> findPurchasesByPatientInMonthsPeriod(Integer months);
}
