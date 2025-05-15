package com.ruoyi.suyuan.domain;

public class ScanLogPName {
    private String name;
    private Integer count;

    // getter/setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }

    @Override
    public String toString() {
        return "ScanLogCName{" +
                "name='" + getName() + '\'' +
                ", count=" + getCount() +
                '}';
    }
}
