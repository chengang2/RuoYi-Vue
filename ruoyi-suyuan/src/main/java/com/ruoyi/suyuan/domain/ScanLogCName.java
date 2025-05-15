package com.ruoyi.suyuan.domain;

public class ScanLogCName {
    private String cname;
    private Integer count;

    // getter/setter
    public String getCname() { return cname; }
    public void setCname(String cname) { this.cname = cname; }
    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }

    @Override
    public String toString() {
        return "ScanLogCName{" +
                "cname='" + getCname() + '\'' +
                ", count=" + getCount() +
                '}';
    }
}
