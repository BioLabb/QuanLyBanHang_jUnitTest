//List du lieu thu cua tab thong ke theo quy

package com.qlbh;

public class ProfitQuarter {
    private int quarter;
    private double profit;

    public ProfitQuarter() {
    }

    public ProfitQuarter(int quarter, double profit) {
        this.quarter = quarter;
        this.profit = profit;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
