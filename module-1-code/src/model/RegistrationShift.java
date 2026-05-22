package model;

import java.io.Serializable;
import java.util.Date;

public class RegistrationShift implements Serializable {
    private int id;
    private Date registeredTime;
    private String status;
    private String description;
    private Employee employee;
    private Shift shift;
    private User user;

    public RegistrationShift() {
    }

    public RegistrationShift(int id, Date registeredTime, String status, String description, Employee employee, Shift shift, User user) {
        this.id = id;
        this.registeredTime = registeredTime;
        this.status = status;
        this.description = description;
        this.employee = employee;
        this.shift = shift;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(Date registeredTime) {
        this.registeredTime = registeredTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
