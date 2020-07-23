package com.ironhack.SellService.service;

import com.ironhack.SellService.exceptions.ResourceNotFoundException;
import com.ironhack.SellService.model.MedicineSold;
import com.ironhack.SellService.model.Sales;
import com.ironhack.SellService.repository.MedicineSoldRepository;
import com.ironhack.SellService.repository.SalesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineSoldService {
    private static final Logger LOGGER = LogManager.getLogger(MedicineSoldService.class);

    @Autowired
    private MedicineSoldRepository medicineSoldRepository;
    @Autowired
    private SalesRepository salesRepository;

    public List<MedicineSold> findAll() {
        LOGGER.info("GET request to retrieve all medicines sold");
        return medicineSoldRepository.findAllByOrderBySalesIdDesc();
    }

    public MedicineSold findById(Long id) {
        LOGGER.info("GET request to retrieve medicine sold by id. Id: " + id);
        return medicineSoldRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Medicine Sold not found with that id")
        );
    }

    public List<MedicineSold> findBySalesId(Long id) {
        LOGGER.info("GET request to retrieve medicines sold by sales-Id. Sales-Id: " + id);
        Sales sales = salesRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sales not found with that id")
        );
        return medicineSoldRepository.findAllBySalesIdEquals(id);
    }

    public List<MedicineSold> storeMedicinesSold(List<MedicineSold> medicines) {
        LOGGER.info("POST request to store new medicines sold");
        return medicineSoldRepository.saveAll(medicines);
    }

}
