package model;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    private int id;
    private String code;
    private String phoneNumber;
    private String fullName;
    private String email;
    private Date dob;
    private Date contractDate;
    private Restaurant restaurant;

    public Employee() {
    }

    public Employee(int id, String code, String phoneNumber, String fullName, String email, Date dob, Date contractDate) {
        this.id = id;
        this.code = code;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.email = email;
        this.dob = dob;
        this.contractDate = contractDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
