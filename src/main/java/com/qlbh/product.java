package com.qlbh;

public class product {
    private int Number;
    private String ID;
    private String nameProduct;
    private int amount;
    private String dv;
    private double gia;
    private double thanhTien;
    private double mua;

    public product() {
    }



    public product(int number, String ID, String nameProduct, int amount, String dv, double gia, double thanhTien, double mua) {
        Number = number;
        this.ID = ID;
        this.nameProduct = nameProduct;
        this.amount = amount;
        this.dv = dv;
        this.gia = gia;
        this.thanhTien = thanhTien;
        this.mua = mua;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public double getMua() {
        return mua;
    }

    public void setMua(double mua) {
        this.mua = mua;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
}
