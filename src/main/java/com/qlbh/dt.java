package com.qlbh;

import java.math.BigDecimal;
import java.util.Date;

public class dt {
    private int ID;
    private String MaSP;
    private String TenSP;
    private int soLuong;
    private String dv;
    private BigDecimal Gia;
    private BigDecimal ThanhTien;
    private BigDecimal Mua;
    private Date Date;


    public dt(int ID, String maSP, String tenSP, int soLuong, String dv, BigDecimal gia, BigDecimal thanhTien, BigDecimal mua, java.util.Date date) {
        this.ID = ID;
        MaSP = maSP;
        TenSP = tenSP;
        this.soLuong = soLuong;
        this.dv = dv;
        Gia = gia;
        ThanhTien = thanhTien;
        Mua = mua;
        Date = date;
    }

    public dt() {
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public BigDecimal getGia() {
        return Gia;
    }

    public void setGia(BigDecimal gia) {
        Gia = gia;
    }

    public BigDecimal getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        ThanhTien = thanhTien;
    }

    public BigDecimal getMua() {
        return Mua;
    }

    public void setMua(BigDecimal mua) {
        Mua = mua;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }
}
