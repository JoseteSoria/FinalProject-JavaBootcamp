package com.ironhack.SellService.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class MedicineSold {
    @Id
    private Long id;
    @NotNull(message = "Medicine Id can not be null")
    private Long medicineId;
    @NotNull(message = "Sales Id can not be null")
    private Long salesId;
    @NotNull(message = "Quantity can not be null")
    private Integer quantity;

    public MedicineSold(){}

    public MedicineSold(Long medicineId, Long salesId, Integer quantity) {
        this.medicineId = medicineId;
        this.salesId = salesId;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
