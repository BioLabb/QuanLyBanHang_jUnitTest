package com.qlbh;

public class TableProduct {
    private int productID;
    private String productName;
    private int supplierID;
    private double cost;
    private double price;
    private int quantity;

    public TableProduct(int productID, String productName, int supplierID, double cost, double price, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.supplierID = supplierID;
        this.cost = cost;
        this.price = price;
        this.quantity = quantity;
    }

    public TableProduct(int productID, String productName, int supplierID, double cost, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.supplierID = supplierID;
        this.cost = cost;
        this.quantity = quantity;
    }


    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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
}
