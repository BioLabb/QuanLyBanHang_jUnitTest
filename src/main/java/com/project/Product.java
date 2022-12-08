package com.project;

public class Product {
    private int id;
    private String name;

    private int supplierID;
    private double price;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    private double cost;
    private int quantity;

    public Product(int id, String name, int supplierID, double price, double cost) {
        this.id = id;
        this.name = name;
        this.supplierID = supplierID;
        this.price = price;
        this.cost = cost;
    }

    public Product(int id, String name, int supplierID, double price, double cost, int quantity) {
        this.id = id;
        this.name = name;
        this.supplierID = supplierID;
        this.price = price;
        this.cost = cost;
        this.quantity = quantity;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
