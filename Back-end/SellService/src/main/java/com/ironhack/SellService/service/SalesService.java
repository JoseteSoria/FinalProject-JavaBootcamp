package com.ironhack.SellService.service;

import com.ironhack.SellService.exceptions.ResourceNotFoundException;
import com.ironhack.SellService.model.Sales;
import com.ironhack.SellService.repository.SalesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {
    private static final Logger LOGGER = LogManager.getLogger(SalesService.class);

    @Autowired
    private SalesRepository salesRepository;

    public List<Sales> findAll() {
        LOGGER.info("GET request to retrieve all sales");
        return salesRepository.findAllByOrderByDateDesc();
    }

    public Sales findById(Long id) {
        LOGGER.info("GET request to retrieve sale by id. Id: " + id);
        return salesRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sales not found with that id")
        );
    }

    public Sales findLastSale() {
        LOGGER.info("GET request to retrieve last sale.");
        return salesRepository.findLastSale();
    }

    public Sales store(Sales sales) {
        LOGGER.info("POST request to store a new sale.");
        return salesRepository.save(sales);
    }
}
