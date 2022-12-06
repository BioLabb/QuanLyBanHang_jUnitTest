package com.project;

import java.util.Date;

public class Orders {

    private int orderId;
    private int employee_Id;
    private int customerId;
    private Date orderDate;
    private double taxes;
    public Orders(int orderId, int employee_Id, int customerId) {
        this.orderId = orderId;
        this.employee_Id = employee_Id;
        this.customerId = customerId;
    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getEmployee_Id() {
        return employee_Id;
    }

    public void setEmployee_Id(int employee_Id) {
        this.employee_Id = employee_Id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

}
