package com.ruoyi.suyuan.domain;

public class OperateLogScoreProductMonth {
    private String name;
    private Integer rate;
    private Integer month;

    // getter/setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getRate() { return rate; }
    public void setRate(Integer rate) { this.rate = rate; }
    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }

    @Override
    public String toString() {
        return "OperateLogScoreProductMonth{" +
                "name='" + getName() + '\'' +
                ", rate=" + getRate() +
                ", month=" + getMonth() +
                '}';
    }
}
