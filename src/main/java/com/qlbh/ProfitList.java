//Day la List va du lieu thu cua Hieu

package com.qlbh;

import java.util.Date;

public class ProfitList {
    private Date oneDay;
    private double profit;

    public ProfitList() {
    }

    public ProfitList(Date oneDay, double profit) {
        this.oneDay = oneDay;
        this.profit = profit;
    }

    public Date getOneDay() {
        return oneDay;
    }

    public void setOneDay(Date oneDay) {
        this.oneDay = oneDay;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
