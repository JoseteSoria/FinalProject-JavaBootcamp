package com.ironhack.OrderService.model;

import com.ironhack.OrderService.classes.Money;

import javax.persistence.*;
import java.util.Calendar;
import java.util.TimeZone;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "total_price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency")),
    })
    private Money totalPrice;

    public Order() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        date = cal;
    }

    public Order(Money totalPrice) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
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
