package com.ironhack.PharmacyEdge.model.sell;

import com.ironhack.PharmacyEdge.classes.Money;

import javax.validation.constraints.NotNull;

public class MedicineSold {
    private Long id;
    @NotNull(message = "Medicine Id can not be null")
    private Long medicineId;
    @NotNull(message = "Name can not be null")
    private String name;
    @NotNull(message = "Money can not be null")
    private Money price;
    @NotNull(message = "Sales Id can not be null")
    private Long salesId;

    public MedicineSold() {
    }

    public MedicineSold(Long medicineId, String name, Long salesId) {
        this.medicineId = medicineId;
        this.name = name;
        this.salesId = salesId;
    }

    public MedicineSold(Long medicineId, String name, Money price, Long salesId) {
        this.medicineId = medicineId;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
