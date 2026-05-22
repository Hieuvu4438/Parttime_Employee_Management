package model;

import java.io.Serializable;
import java.util.Date;

public class Shift implements Serializable {
    private int id;
    private Date date;
    private int shiftNumber;
    private String description;
    private Date startDate;
    private Date endDate;

    public Shift() {
    }

    public Shift(int id, Date date, int shiftNumber, String description, Date startDate, Date endDate) {
        this.id = id;
        this.date = date;
        this.shiftNumber = shiftNumber;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
