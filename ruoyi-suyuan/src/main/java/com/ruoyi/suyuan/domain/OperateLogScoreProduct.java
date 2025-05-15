package com.ruoyi.suyuan.domain;

public class OperateLogScoreProduct {
    private String name;
    private Integer rate;

    // getter/setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRate() { return rate; }
    public void setRate(Integer rate) { this.rate = rate; }

    @Override
    public String toString() {
        return "OperateLogScoreProduct{" +
                "name=" + getName() +
                ", rate=" + getRate() +
                '}';
    }
}
