package com.ironhack.ReportService.service;

import com.ironhack.ReportService.repository.MedicineSoldRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class MedicineSoldService {
    private static final Logger LOGGER = LogManager.getLogger(MedicineSoldService.class);

    @Autowired
    private MedicineSoldRepository medicineSoldRepository;

    public List<Object[]> findMoreMedicineSoldPeriodRanking(Integer months, Integer ranking) {
        LOGGER.info("GET request to report " + ranking + " best medicine sold in last " + months + " months.");
        Calendar cal = Calendar.getInstance();
        if (months < 12) cal.add(Calendar.MONTH, -months);
        else {
            cal.add(Calendar.YEAR, -months / 12);
            cal.add(Calendar.MONTH, -months % 12);
        }
        return medicineSoldRepository.findMoreMedicineSoldPeriodRanking(cal, ranking);
    }

    public List<Object[]> findMoreMedicineRevenuePeriodRanking(Integer months, Integer ranking) {
        LOGGER.info("GET request to report " + ranking + " best medicines in revenue terms in last " + months + " months.");
        Calendar cal = Calendar.getInstance();
        if (months < 12) cal.roll(Calendar.MONTH, -months);
        else {
            cal.roll(Calendar.YEAR, -months / 12);
            cal.roll(Calendar.MONTH, -months % 12);
        }
        return medicineSoldRepository.findMoreMedicineRevenuePeriodRanking(cal, ranking);
    }
}
