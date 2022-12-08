//List du lieu thu cua tab thong ke theo quy

package com.qlbh;

public class ProfitQuarter {
    private String quarter;
    private double profit;

    private int year;

    public ProfitQuarter() {
    }

    public ProfitQuarter(String quarter, double profit, int year) {
        this.quarter = quarter;
        this.profit = profit;
        this.year = year;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
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

    //    public ProfitQuarter() {
//    }
//
//    public ProfitQuarter(String quarter, double profit) {
//        this.quarter = quarter;
//        this.profit = profit;
//    }
//
//    public String getQuarter() {
//        return quarter;
//    }
//
//    public void setQuarter(String quarter) {
//        this.quarter = quarter;
//    }
//
//    public double getProfit() {
//        return profit;
//    }
//
//    public void setProfit(double profit) {
//        this.profit = profit;
//    }

    //    public ProfitQuarter(int quarter, double profit) {
//        this.quarter = quarter;
//        this.profit = profit;
//    }
//
//    public int getQuarter() {
//        return quarter;
//    }
//
//    public void setQuarter(int quarter) {
//        this.quarter = quarter;
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
