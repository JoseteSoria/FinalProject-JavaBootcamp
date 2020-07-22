package com.ironhack.ReportService.service;

import com.ironhack.ReportService.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class SalesService {
    @Autowired
    private SalesRepository salesRepository;

    public List<Object[]> findSalesByUserInMonthsPeriod(Integer months){
        Calendar cal = Calendar.getInstance();
        if(months < 12) cal.add(Calendar.MONTH, -months);
        else {
            cal.add(Calendar.YEAR, -months/12);
            cal.add(Calendar.MONTH, -months%12);
        }
        return salesRepository.findSalesByUserInMonthsPeriod(cal);
    }

    public List<Object[]> findPurchasesByPatientInMonthsPeriod(Integer months){
        Calendar cal = Calendar.getInstance();
        if(months < 12) cal.roll(Calendar.MONTH, -months);
        else {
            cal.roll(Calendar.YEAR, -months/12);
            cal.roll(Calendar.MONTH, -months%12);
        }
        return salesRepository.findPurchasesByPatientInMonthsPeriod(cal);
    }
}
