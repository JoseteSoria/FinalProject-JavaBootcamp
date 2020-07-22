package com.ironhack.ReportService.controller.interfaces;

import java.util.List;

public interface IMedicineSoldController {

    public List<Object[]> findMoreMedicineSoldPeriodRanking(Integer months, Integer ranking);

    public List<Object[]> findMoreMedicineRevenuePeriodRanking(Integer months, Integer ranking);
}
