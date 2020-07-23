package com.ironhack.ReportService.service;

import com.ironhack.ReportService.repository.SalesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class SalesService {
    private static final Logger LOGGER = LogManager.getLogger(SalesService.class);

    @Autowired
    private SalesRepository salesRepository;

    public List<Object[]> findSalesByUserInMonthsPeriod(Integer months){
        LOGGER.info("GET request to report sales by user in last " + months + " months.");
        Calendar cal = Calendar.getInstance();
        if(months < 12) cal.add(Calendar.MONTH, -months);
        else {
            cal.add(Calendar.YEAR, -months/12);
            cal.add(Calendar.MONTH, -months%12);
        }
        return salesRepository.findSalesByUserInMonthsPeriod(cal);
    }

    public List<Object[]> findPurchasesByPatientInMonthsPeriod(Integer months){
        LOGGER.info("GET request to report purchases by patient in last " + months + " months.");
        Calendar cal = Calendar.getInstance();
        if(months < 12) cal.roll(Calendar.MONTH, -months);
        else {
            cal.roll(Calendar.YEAR, -months/12);
            cal.roll(Calendar.MONTH, -months%12);
        }
        return salesRepository.findPurchasesByPatientInMonthsPeriod(cal);
    }
}
