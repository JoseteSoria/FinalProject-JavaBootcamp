package com.ironhack.MedicineService.model.dto;

public class MedicinesToStoreDTO {
    private Long medicineId;
    private Integer quantity;

    public MedicinesToStoreDTO(Long medicineId, Integer quantity) {
        setMedicineId(medicineId);
        setQuantity(quantity);
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
}
