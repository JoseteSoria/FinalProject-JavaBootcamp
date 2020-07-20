package com.ironhack.PharmacyEdge.model.order;

import com.ironhack.PharmacyEdge.classes.Money;

import java.util.Calendar;

public class Order {
    private Long id;
    private Calendar date;
    private Money totalPrice;

    public Order() {
        Calendar cal = Calendar.getInstance();
        date = cal;
    }

    public Order(Money totalPrice) {
        Calendar cal = Calendar.getInstance();
        date = cal;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Money totalPrice) {
        this.totalPrice = totalPrice;
    }
}
