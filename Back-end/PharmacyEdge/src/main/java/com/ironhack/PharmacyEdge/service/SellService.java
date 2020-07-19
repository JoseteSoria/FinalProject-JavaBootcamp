package com.ironhack.PharmacyEdge.service;

import com.ironhack.PharmacyEdge.client.SellClient;
import com.ironhack.PharmacyEdge.model.sell.MedicineSold;
import com.ironhack.PharmacyEdge.model.sell.Sales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellService {
    @Autowired
    SellClient sellClient;

    public List<Sales> findAllSales() {
        return sellClient.findAllSales();
    }

    public Sales findSaleById(Long id) {
        return sellClient.findSaleById(id);
    }

    public Sales findLastSale() {
        return sellClient.findLastSale();
    }

    public Sales createSale(Sales sales) {
        return sellClient.createSale(sales);
    }

    public List<MedicineSold> findAllMedicinesSold() {
        return sellClient.findAllMedicinesSold();
    }

    public MedicineSold findMedicineSoldById(Long id) {
        return sellClient.findMedicineSoldById(id);
    }

    public List<MedicineSold> findMedicineSoldBySalesId(Long id) {
        return sellClient.findMedicineSoldBySalesId(id);
    }

    public List<MedicineSold> storeMedicinesSold(List<MedicineSold> medicines) {
        return sellClient.createMedicineSold(medicines);
    }

}
