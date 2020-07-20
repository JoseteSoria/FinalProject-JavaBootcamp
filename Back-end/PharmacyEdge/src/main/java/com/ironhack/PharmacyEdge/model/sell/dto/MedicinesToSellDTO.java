package com.ironhack.PharmacyEdge.model.sell.dto;

public class MedicinesToSellDTO {
    private Long warehouseMedicineId;
    private Integer userId;
    private Integer PatientId;

    public MedicinesToSellDTO(Long warehouseMedicineId, Integer userId, Integer patientId) {
        this.warehouseMedicineId = warehouseMedicineId;
        this.userId = userId;
        PatientId = patientId;
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
        return PatientId;
    }

    public void setPatientId(Integer patientId) {
        PatientId = patientId;
    }
}
