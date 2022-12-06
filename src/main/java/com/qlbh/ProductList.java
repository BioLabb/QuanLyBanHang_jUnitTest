package com.qlbh;

import java.sql.Date;

public class ProductList {
    private int STT;
    private String ID;
    private String Name;
    private int Amout;
    private String Quantity;
    private double Price;
    private Date Datet;

    public ProductList() {
    }


    public ProductList(int STT, String ID, String name, int amout, String quantity, double price, Date datet) {
        this.STT = STT;
        this.ID = ID;
        Name = name;
        Amout = amout;
        Quantity = quantity;
        Price = price;
        Datet = datet;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAmout() {
        return Amout;
    }

    public void setAmout(int amout) {
        Amout = amout;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public Date getDatet() {
        return Datet;
    }

    public void setDatet(Date datet) {
        Datet = datet;
    }
}
