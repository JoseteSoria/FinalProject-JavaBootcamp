package com.ironhack.MedicineService.model.dto;

public class MedicinesToSellDTO {
    private Long warehouseMedicineId;
    private Integer userId;
    private Integer patientId;

    public MedicinesToSellDTO(Long warehouseMedicineId, Integer userId, Integer patientId) {
        this.warehouseMedicineId = warehouseMedicineId;
        this.userId = userId;
        this.patientId = patientId;
    }

    public Long getWarehouseMedicineId() {
        return warehouseMedicineId;
    }

    public void setWarehouseMedicineId(Long warehouseMedicineId) {
        this.warehouseMedicineId = warehouseMedicineId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}

