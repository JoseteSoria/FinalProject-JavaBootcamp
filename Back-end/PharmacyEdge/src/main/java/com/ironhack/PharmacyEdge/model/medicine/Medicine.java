package com.ironhack.PharmacyEdge.model.medicine;


import com.ironhack.PharmacyEdge.classes.Money;

import javax.validation.constraints.NotNull;

public class Medicine {
    private Long id;
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Month Duration is required")
    private Integer monthDuration;
    @NotNull(message = "Generic Boolean is required")
    private Boolean generic;
    @NotNull(message = "Minimum price is required")
    private Money minimumPrice;

    public Medicine(){}

    public Medicine(String name, Integer monthDuration, Boolean generic, Money recommendedPrice) {
        this.name = name;
        this.monthDuration = monthDuration;
        this.generic = generic;
        this.minimumPrice = recommendedPrice;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMonthDuration() {
        return monthDuration;
    }

    public Boolean getGeneric() {
        return generic;
    }

    public Money getMinimumPrice() {
        return minimumPrice;
    }

    /**Setters will not be used in our application because this table is as if it were a catalog**/

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
    public void setMonthDuration(Integer monthDuration){
        this.monthDuration = monthDuration;
    }

    public void setGeneric(Boolean generic) {
        this.generic = generic;
    }

    public void setMinimumPrice(Money minimumPrice) {
        this.minimumPrice = minimumPrice;
    }**/
}
