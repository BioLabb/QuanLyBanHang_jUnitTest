package com.project;

import java.util.ArrayList;
import java.sql.Date;

public class OrderDetails {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private double price;
    private double discount;
    private Date date;
    private ArrayList<Orders> orders = new ArrayList<>();

    public OrderDetails(int id, int orderId, int productId, int quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetails(int orderId, int productId, int quantity, double price, Date date) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public void setOrders(Orders orders) {
        this.orders.add(orders);
    }
    public ArrayList<Orders> getOrders() {
        return orders;
    }

    private double disCount;

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setOrders(ArrayList<Orders> orders) {
        this.orders = orders;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDisCount() {
        return disCount;
    }

    public void setDisCount(double disCount) {
        this.disCount = disCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
