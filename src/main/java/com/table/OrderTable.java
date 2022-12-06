package com.table;

public class OrderTable {
    //    private int id;
    int productID;
    private String productName;
    private int productQuantity;
    private double productPrice;
    private double total;

    public OrderTable(int productID, int id, String productName, int productQuantity, double price, double total) {

        this.productID = productID;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = price;
        this.total = total;
    }

    public OrderTable() {
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

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

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
