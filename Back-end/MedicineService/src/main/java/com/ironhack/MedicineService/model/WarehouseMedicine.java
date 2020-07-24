package com.ironhack.MedicineService.model;

import com.ironhack.MedicineService.classes.Money;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
@Table(name = "warehouse_medicine")
public class WarehouseMedicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name is required")
    private String name;
    private Integer monthDuration;
    private Boolean generic;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "min_price")),
            @AttributeOverride(name = "currency", column = @Column(name = "min_currency")),
    })
    private Money minimumPrice;
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

    public WarehouseMedicine(String name, Integer monthDuration, Boolean generic, Money minimumPrice, Money price) {
        this.name = name;
        this.monthDuration = monthDuration;
        this.generic = generic;
        this.minimumPrice = minimumPrice;
        setExpirationDate();
        this.price = price;
    }

    public WarehouseMedicine(Medicine medicine){
        this.name = medicine.getName();
        this.monthDuration = medicine.getMonthDuration();
        this.generic = medicine.getGeneric();
        this.minimumPrice = medicine.getMinimumPrice();
        setExpirationDate();
        this.price = medicine.getMinimumPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMonthDuration() {
        return monthDuration;
    }

    public void setMonthDuration(Integer monthDuration) {
        this.monthDuration = monthDuration;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Boolean getGeneric() {
        return generic;
    }

    public void setGeneric(Boolean generic) {
        this.generic = generic;
    }

    public Money getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(Money minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }
}
