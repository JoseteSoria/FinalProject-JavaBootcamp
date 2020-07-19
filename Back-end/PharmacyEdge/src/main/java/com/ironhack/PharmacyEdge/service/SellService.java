package com.ironhack.PharmacyEdge.service;

import com.ironhack.PharmacyEdge.client.SellClient;
import com.ironhack.PharmacyEdge.exceptions.SellServiceDownException;
import com.ironhack.PharmacyEdge.model.sell.MedicineSold;
import com.ironhack.PharmacyEdge.model.sell.Sales;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellService {
    private static final Logger LOGGER = LogManager.getLogger(SellService.class);

    @Autowired
    SellClient sellClient;

    @HystrixCommand(fallbackMethod = "errorFindAllSales")
    public List<Sales> findAllSales() {
        LOGGER.info("GET request to retrieve all sales");
        return sellClient.findAllSales();
    }

    public List<Sales> errorFindAllSales() {
        LOGGER.error("Controlled exception - fail in GET request to retrieve all sales");
        throw new SellServiceDownException(" Sell Service Down. Method findAllSales. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindSaleById")
    public Sales findSaleById(Long id) {
        LOGGER.info("GET request to retrieve sale by Id. Id: " + id);
        return sellClient.findSaleById(id);
    }

    public Sales errorFindSaleById(Long id) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve sale by Id. Id: " + id);
        throw new SellServiceDownException(" Sell Service Down. Method findSaleById. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindLastSale")
    public Sales findLastSale() {
        LOGGER.info("GET request to retrieve last sale");
        return sellClient.findLastSale();
    }

    public Sales errorFindLastSale() {
        LOGGER.error("Controlled exception - fail in GET request to retrieve last sale");
        throw new SellServiceDownException(" Sell Service Down. Method findLastSale. ");
    }

    @HystrixCommand(fallbackMethod = "errorCreateSale")
    public Sales createSale(Sales sales) {
        LOGGER.info("POST request to create a new sale");
        return sellClient.createSale(sales);
    }

    public Sales errorCreateSale(Sales sales) {
        LOGGER.error("Controlled exception - fail in POST request to create a new sale");
        throw new SellServiceDownException(" Sell Service Down. Method createSale. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindAllMedicinesSold")
    public List<MedicineSold> findAllMedicinesSold() {
        LOGGER.info("GET request to retrieve all medicines sold");
        return sellClient.findAllMedicinesSold();
    }

    public List<MedicineSold> errorFindAllMedicinesSold() {
        LOGGER.error("Controlled exception - fail in GET request to retrieve all medicines sold");
        throw new SellServiceDownException(" Sell Service Down. Method findAllMedicinesSold. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindMedicineSoldById")
    public MedicineSold findMedicineSoldById(Long id) {
        LOGGER.info("GET request to retrieve medicine-sold by id. Id: " + id);
        return sellClient.findMedicineSoldById(id);
    }

    public MedicineSold errorFindMedicineSoldById(Long id) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve medicine-sold by id. Id: " + id);
        throw new SellServiceDownException(" Sell Service Down. Method findMedicineSoldById. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindMedicineSoldBySalesId")
    public List<MedicineSold> findMedicineSoldBySalesId(Long id) {
        LOGGER.info("GET request to retrieve medicine-sold by salesId. salesId: " + id);
        return sellClient.findMedicineSoldBySalesId(id);
    }

    public List<MedicineSold> errorFindMedicineSoldBySalesId(Long id) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve medicine-sold by salesId. salesId: " + id);
        throw new SellServiceDownException(" Sell Service Down. Method findMedicineSoldBySalesId. ");
    }

    @HystrixCommand(fallbackMethod = "errorStoreMedicinesSold")
    public List<MedicineSold> storeMedicinesSold(List<MedicineSold> medicines) {
        LOGGER.info("POST request to add new medicines sold");
        return sellClient.createMedicineSold(medicines);
    }

    public List<MedicineSold> errorStoreMedicinesSold(List<MedicineSold> medicines) {
        LOGGER.error("Controlled exception - fail in POST request to add new medicines sold");
        throw new SellServiceDownException(" Sell Service Down. Method storeMedicinesSold. ");
    }
}
