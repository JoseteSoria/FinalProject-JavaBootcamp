package com.ironhack.PharmacyEdge.controller.interfaces;

import java.util.List;

public interface IReportController {

    public List<Object[]> findSalesByUserInMonthsPeriod(Integer months);

    public List<Object[]> findPurchasesByPatientInMonthsPeriod(Integer months);

    public List<Object[]> findMoreMedicineSoldPeriodRanking(Integer months, Integer ranking);

    public List<Object[]> findMoreMedicineRevenuePeriodRanking(Integer months, Integer ranking);
}
