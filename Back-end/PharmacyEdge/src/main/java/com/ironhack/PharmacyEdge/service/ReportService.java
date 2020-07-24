package com.ironhack.PharmacyEdge.service;

import com.ironhack.PharmacyEdge.client.ReportClient;
import com.ironhack.PharmacyEdge.exceptions.ReportServiceDownException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private static final Logger LOGGER = LogManager.getLogger(ReportService.class);

    @Autowired
    ReportClient reportClient;

    @HystrixCommand(fallbackMethod = "errorFindSalesByUserInMonthsPeriod")
    public List<Object[]> findSalesByUserInMonthsPeriod(Integer months) {
        LOGGER.info("GET request to retrieve sales by user in last " + months + " months");
        return reportClient.findSalesByUserInMonthsPeriod(months);
    }

    public List<Object[]> errorFindSalesByUserInMonthsPeriod(Integer months) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve sales by user. ");
        throw new ReportServiceDownException("Report Service Down. Method findSalesByUserInMonthsPeriod. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindPurchasesByPatientInMonthsPeriod")
    public List<Object[]> findPurchasesByPatientInMonthsPeriod(Integer months) {
        LOGGER.info("GET request to retrieve purchases by patient in last " + months + " months");
        return reportClient.findPurchasesByPatientInMonthsPeriod(months);
    }

    public List<Object[]> errorFindPurchasesByPatientInMonthsPeriod(Integer months) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve purchases by patient. ");
        throw new ReportServiceDownException("Report Service Down. Method findPurchasesByPatientInMonthsPeriod. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindMoreMedicineSoldPeriodRanking")
    public List<Object[]> findMoreMedicineSoldPeriodRanking(Integer months, Integer ranking) {
        LOGGER.info("GET request to retrieve the " + ranking + " best sold medicines" +
                "in the last " + months + " months");
        return reportClient.findMoreMedicineSoldPeriodRanking(months, ranking);
    }

    public List<Object[]> errorFindMoreMedicineSoldPeriodRanking(Integer months, Integer ranking) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve best sold medicines. ");
        throw new ReportServiceDownException("Report Service Down. Method findSalesByUserInMonthsPeriod. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindMoreMedicineRevenuePeriodRanking")
    public List<Object[]> findMoreMedicineRevenuePeriodRanking(Integer months, Integer ranking) {
        LOGGER.info("GET request to retrieve the " + ranking + " best medicines in income aspects" +
                "in the last " + months + " months");
        return reportClient.findMoreMedicineRevenuePeriodRanking(months, ranking);
    }

    public List<Object[]> errorFindMoreMedicineRevenuePeriodRanking(Integer months, Integer ranking) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve best (revenue) medicines. ");
        throw new ReportServiceDownException("Report Service Down. Method findMoreMedicineRevenuePeriodRanking. ");
    }

}
