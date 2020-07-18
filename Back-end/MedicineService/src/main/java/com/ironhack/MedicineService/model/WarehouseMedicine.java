package com.ironhack.MedicineService.model;

import com.ironhack.MedicineService.classes.Money;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "warehouse_medicine")
public class WarehouseMedicine extends Medicine {
    private Integer quantity;
    @Temporal(TemporalType.DATE)
    private Calendar expirationDate;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency")),
    })
    private Money price;

    public WarehouseMedicine() {
    }

    public WarehouseMedicine(Medicine medicine, Integer quantity){
        super(medicine.getName(), medicine.getMonthDuration(), medicine.getGeneric(), medicine.getMinimumPrice());
        this.quantity= quantity;
        setExpirationDate();
        this.price = this.getMinimumPrice();
    }

    public WarehouseMedicine(String name, Integer monthDuration, Boolean generic, Money recommendedPrice, Integer quantity, Money price) {
        super(name, monthDuration, generic, recommendedPrice);
        this.quantity = quantity;
        setExpirationDate();
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate() {
        if(this.getMonthDuration()<=0){
            this.expirationDate = Calendar.getInstance();
            this.expirationDate.add(Calendar.YEAR, 10);
            return;
        }
        this.expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.MONTH,this.getMonthDuration());
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
