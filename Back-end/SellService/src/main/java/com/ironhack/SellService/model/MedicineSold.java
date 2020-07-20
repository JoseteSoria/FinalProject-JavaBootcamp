package com.ironhack.SellService.model;

import com.ironhack.SellService.classes.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class MedicineSold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Medicine Id can not be null")
    private Long medicineId;
    @NotNull(message = "Medicine name can not be null")
    private String medicineName;
    @NotNull(message = "Money can not be null")
    private Money price;
    @NotNull(message = "Sales Id can not be null")
    private Long salesId;

    public MedicineSold(){}

    public MedicineSold(Long medicineId, String medicineName, Long salesId) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.salesId = salesId;
    }

    public MedicineSold(Long medicineId, String medicineName, Money price, Long salesId) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.price = price;
        this.salesId = salesId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Long getSalesId() {
        return salesId;
    }

    public void setSalesId(Long salesId) {
        this.salesId = salesId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
