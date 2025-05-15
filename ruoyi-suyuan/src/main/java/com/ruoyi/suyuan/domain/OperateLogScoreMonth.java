package com.ruoyi.suyuan.domain;

public class OperateLogScoreMonth {
    private Integer month;
    private Integer rate;

    // getter/setter
    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }
    public Integer getRate() { return rate; }
    public void setRate(Integer rate) { this.rate = rate; }

    @Override
    public String toString() {
        return "OperateLogScoreMonth{" +
                "month=" + getMonth() +
                ", rate=" + getRate() +
                '}';
    }
}
