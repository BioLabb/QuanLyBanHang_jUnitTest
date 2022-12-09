//List du lieu thu cua tab thong ke theo thang

package com.qlbh;

public class ProfitListMonth {
    private int Month;
    private double profit;

    private int year;

    public ProfitListMonth() {
    }

    public ProfitListMonth(int month, double profit, int year) {
        Month = month;
        this.profit = profit;
        this.year = year;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    //    public ProfitListMonth(String month, double profit, int year) {
//        Month = month;
//        this.profit = profit;
//        this.year = year;
//    }
//
//    public String getMonth() {
//        return Month;
//    }
//
//    public void setMonth(String month) {
//        Month = month;
//    }
//
//    public double getProfit() {
//        return profit;
//    }
//
//    public void setProfit(double profit) {
//        this.profit = profit;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//    public void setYear(int year) {
//        this.year = year;
//    }

    //    public ProfitListMonth(String month, double profit) {
//        Month = month;
//        this.profit = profit;
//    }
//
//    public String getMonth() {
//        return Month;
//    }
//
//    public void setMonth(String month) {
//        Month = month;
//    }
//
//    public double getProfit() {
//        return profit;
//    }
//
//    public void setProfit(double profit) {
//        this.profit = profit;
//    }
    //    public ProfitListMonth(int month, double profit) {
//        Month = month;
//        this.profit = profit;
//    }
//
//    public int getMonth() {
//        return Month;
//    }
//
//    public void setMonth(int month) {
//        Month = month;
//    }
//
//    public double getProfit() {
//        return profit;
//    }
//
//    public void setProfit(double profit) {
//        this.profit = profit;
//    }
}
