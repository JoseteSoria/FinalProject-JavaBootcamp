package com.ironhack.PharmacyEdge.controller.implementations;

import com.ironhack.PharmacyEdge.controller.interfaces.IMedicineController;
import com.ironhack.PharmacyEdge.model.medicine.Medicine;
import com.ironhack.PharmacyEdge.model.medicine.WarehouseMedicine;
import com.ironhack.PharmacyEdge.model.medicine.viewModel.WarehouseMedicineQuantityVM;
import com.ironhack.PharmacyEdge.model.order.dto.MedicinesToStoreDTO;
import com.ironhack.PharmacyEdge.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MedicineController implements IMedicineController {
    @Autowired
    MedicineService medicineService;

    @GetMapping("/medicines")
    @ResponseStatus(HttpStatus.OK)
    public List<Medicine> findAllMedicines(){
        return medicineService.findAllMedicines();
    }

    @GetMapping("/medicines/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Medicine findMedicineById(@PathVariable Long id){
        return medicineService.findMedicineById(id);
    }

    @GetMapping( "/medicines/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Medicine> findMedicineByName(@PathVariable(name = "name") String name){
        return medicineService.findMedicineByName(name);
    }

    @GetMapping("/warehouse-medicines")
    @ResponseStatus(HttpStatus.OK)
    public List<WarehouseMedicine> findAllWarehouseMedicines(){
        return medicineService.findAllWarehouseMedicines();
    }

    @GetMapping("/warehouse-medicines/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WarehouseMedicine findWarehouseMedicineById(@PathVariable Long id){
        return medicineService.findWarehouseMedicineById(id);
    }

    @GetMapping("/warehouse-medicines/name/{name}/quantity")
    @ResponseStatus(HttpStatus.OK)
    public Optional<WarehouseMedicineQuantityVM> findQuantityByName(@PathVariable(name = "name") String name){
        return medicineService.findQuantityByName(name);
    }

    @GetMapping("/warehouse-medicines/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<List<WarehouseMedicine>> findWarehouseMedicineByName(@PathVariable(name = "name") String name){
        return medicineService.findWarehouseMedicineByName(name);
    }

    @PostMapping("/warehouse-medicines/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addWarehouseMedicines(@RequestBody List<MedicinesToStoreDTO> medicinesToStoreDTOS){
        medicineService.addWarehouseMedicines(medicinesToStoreDTOS);
    }

    @PutMapping("/warehouse-medicines/{id}/update-price/{price}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePrice(@PathVariable(name = "id") Long id, @PathVariable(name = "price") String price){
        medicineService.updatePrice(id, price);
    }

    @DeleteMapping("/warehouse-medicines/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWarehouseMedicine(@PathVariable(name = "id") Long id){
        medicineService.deleteWarehouseMedicine(id);
    }
}
