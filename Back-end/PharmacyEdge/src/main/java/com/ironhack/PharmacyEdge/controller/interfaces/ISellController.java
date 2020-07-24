package com.ironhack.PharmacyEdge.controller.interfaces;

import com.ironhack.PharmacyEdge.model.sell.MedicineSold;
import com.ironhack.PharmacyEdge.model.sell.Sales;

import java.util.List;

public interface ISellController {
    public List<Sales> findAllSales();

    public Sales findSaleById(Long id);

    public Sales findLastSale();

    public Sales createSale(Sales sales);

    public List<MedicineSold> findAllMedicinesSold();

    public MedicineSold findMedicineSoldById(Long id);

    public List<MedicineSold> findMedicineSoldBySalesId(Long id);

    public List<MedicineSold> createMedicineSold(List<MedicineSold> medicines);
}
