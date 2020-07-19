package com.ironhack.MedicineService.model;

import com.ironhack.MedicineService.classes.Money;

import javax.persistence.*;

@Entity
@Table(name = "medicine")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;
    private Integer monthDuration;
    private Boolean generic;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "min_price")),
            @AttributeOverride(name = "currency", column = @Column(name = "min_currency")),
    })
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
