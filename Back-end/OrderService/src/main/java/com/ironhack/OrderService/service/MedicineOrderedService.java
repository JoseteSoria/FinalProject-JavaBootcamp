package com.ironhack.OrderService.service;

import com.ironhack.OrderService.exceptions.ResourceNotFoundException;
import com.ironhack.OrderService.model.MedicineOrdered;
import com.ironhack.OrderService.model.Order;
import com.ironhack.OrderService.repository.MedicineOrderedRepository;
import com.ironhack.OrderService.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineOrderedService {
    private static final Logger LOGGER = LogManager.getLogger(MedicineOrderedService.class);

    @Autowired
    private MedicineOrderedRepository medicineOrderedRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<MedicineOrdered> findAll() {
        LOGGER.info("GET request to retrieve all medicines ordered");
        return medicineOrderedRepository.findAllByOrderByOrderIdDesc();
    }

    public MedicineOrdered findById(Long id) {
        LOGGER.info("GET request to retrieve medicine ordered by id. Id: " + id);
        return medicineOrderedRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Medicine Ordered not found with that id")
        );
    }

    public List<MedicineOrdered> findByOrderId(Long id) {
        LOGGER.info("GET request to retrieve medicines ordered by order-Id. Order-Id: " + id);
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order not found with that id")
        );
        return medicineOrderedRepository.findAllByOrderIdEquals(id);
    }

    public List<MedicineOrdered> storeMedicines(List<MedicineOrdered> medicines) {
        LOGGER.info("POST request to store new medicines ordered");
        return medicineOrderedRepository.saveAll(medicines);
    }
}
