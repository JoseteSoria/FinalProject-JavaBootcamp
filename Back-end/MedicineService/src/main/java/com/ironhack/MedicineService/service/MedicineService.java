package com.ironhack.MedicineService.service;

import com.ironhack.MedicineService.exceptions.ResourceNotFoundException;
import com.ironhack.MedicineService.model.Medicine;
import com.ironhack.MedicineService.repository.MedicineRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {
    private static final Logger LOGGER = LogManager.getLogger(MedicineService.class);

    @Autowired
    private MedicineRepository medicineRepository;

    public List<Medicine> findAll(){
        LOGGER.info("GET request to retrieve every medicine");
        return medicineRepository.findAll();
    }

    public Medicine findById(Long id){
        LOGGER.info("GET request to retrieve medicine by id. Id: " + id);
        return medicineRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Medicine not found with that id"));
    }

    public Optional<Medicine> findByName(String name){
        LOGGER.info("GET request to retrieve medicine by name. Name: " + name);
        return medicineRepository.findByName(name);
    }
}
