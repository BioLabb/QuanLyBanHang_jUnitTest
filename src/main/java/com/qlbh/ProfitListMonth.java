//List du lieu thu cua tab thong ke theo thang

package com.qlbh;

public class ProfitListMonth {
    private int Month;
    private double profit;

    public ProfitListMonth() {
    }

    public ProfitListMonth(int month, double profit) {
        Month = month;
        this.profit = profit;
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
}
