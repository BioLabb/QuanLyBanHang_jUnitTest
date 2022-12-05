package project;

import java.util.ArrayList;
import java.util.Date;

public class orderDetails {
    private int id;
    private int orderId;
    private int productId;
    private double quantity;
    private double price;
    private Date date;
    private ArrayList<Orders> orders = new ArrayList<>();

    public orderDetails(int id, int orderId, int productId, double quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
    public void setOrders(Orders orders) {
        this.orders.add(orders);
    }
    public ArrayList<Orders> getOrders() {
        return orders;
    }

    private double disCount;

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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
