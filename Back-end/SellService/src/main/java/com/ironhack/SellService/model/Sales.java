package com.ironhack.SellService.model;

import com.ironhack.SellService.classes.Money;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "User Id can not be null")
    private Integer userId;
    private Integer patientId;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "total_price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency")),
    })
    private Money totalPrice;

    public Sales() {
        Calendar cal = Calendar.getInstance();
        this.date = cal;
    }

    public Sales(Integer userId, Integer patientId, Money totalPrice) {
        this.userId = userId;
        setPatientId(patientId);
        Calendar cal = Calendar.getInstance();
        this.date = cal;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.patientId = (patientId != null && patientId >= 2) ? patientId : 1;
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
