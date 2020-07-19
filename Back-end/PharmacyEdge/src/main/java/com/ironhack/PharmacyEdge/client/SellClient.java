package com.ironhack.PharmacyEdge.client;

import com.ironhack.PharmacyEdge.model.sell.MedicineSold;
import com.ironhack.PharmacyEdge.model.sell.Sales;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "sell-service")
public interface SellClient {
    @GetMapping("/sales")
    public List<Sales> findAllSales();

    @GetMapping("/sales/{id}")
    public Sales findSaleById(@PathVariable Long id);

    @GetMapping("/sales/last")
    public Sales findLastSale();

    @PostMapping("/sales")
    public Sales createSale(@RequestBody @Valid Sales sales);

    @GetMapping("/medicines-sold")
    public List<MedicineSold> findAllMedicinesSold();

    @GetMapping("/medicines-sold/{id}")
    public MedicineSold findMedicineSoldById(@PathVariable Long id);

    @GetMapping("/medicines-sold/sales/{id}")
    public List<MedicineSold> findMedicineSoldBySalesId(@PathVariable Long id);

    @PostMapping("/medicines-sold")
    public List<MedicineSold> createMedicineSold(@RequestBody @Valid List<MedicineSold> medicines);
}
