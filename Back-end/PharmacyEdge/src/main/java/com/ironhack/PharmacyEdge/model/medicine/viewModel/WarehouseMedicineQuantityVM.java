package com.ironhack.PharmacyEdge.model.medicine.viewModel;

public class WarehouseMedicineQuantityVM {
    private String name;
    private Integer quantity;

    public WarehouseMedicineQuantityVM(String name, Integer quantity) {
        setName(name);
        setQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
