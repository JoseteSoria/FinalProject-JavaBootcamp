package com.ironhack.PatientService.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Calendar;
import java.util.TimeZone;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @Temporal(TemporalType.DATE)
    private Calendar birthday;
    @NotNull
    @Pattern(regexp = "[0-9]{9}")
    private String phoneNumber;

    public Patient() {
    }

    public Patient(String name, Calendar birthday, String phoneNumber) {
        this.name = name;
        TimeZone timeZone1 = TimeZone.getTimeZone("UTC");
        birthday.setTimeZone(timeZone1);
        this.birthday = birthday;
        setPhoneNumber(phoneNumber);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = (phoneNumber != null) ? phoneNumber : "000000000";
    }
}
