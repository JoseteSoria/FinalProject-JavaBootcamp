package com.ironhack.OrderService.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "medicine_ordered")
public class MedicineOrdered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Order id can not be null")
    private Long orderId;
    @NotNull(message = "Medicine id can not be null")
    private Long medicineId;
    private String medicineName;
    @Positive(message = "Quantity has to be a positive integer")
    private Integer quantity;

    public MedicineOrdered() {
    }

    public MedicineOrdered(Long orderId, Long medicineId, Integer quantity) {
        this.orderId = orderId;
        this.medicineId = medicineId;
        this.quantity = quantity;
    }

    public MedicineOrdered(Long orderId, Long medicineId, String medicineName, Integer quantity) {
        this.orderId = orderId;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.medicineName = medicineName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
}

