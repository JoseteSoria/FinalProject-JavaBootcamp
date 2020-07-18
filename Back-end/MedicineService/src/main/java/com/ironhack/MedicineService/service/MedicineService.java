package com.ironhack.MedicineService.service;

import com.ironhack.MedicineService.exceptions.ResourceNotFoundException;
import com.ironhack.MedicineService.model.Medicine;
import com.ironhack.MedicineService.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    public List<Medicine> findAll(){
        return medicineRepository.findAll();
    }

    public Medicine findById(Long id){
        return medicineRepository.findMedicineById(id).orElseThrow(
                ()->new ResourceNotFoundException("Medicine not found with that id"));
    }

    public Optional<Medicine> findByName(String name){
        return medicineRepository.findByName(name);
    }
}
