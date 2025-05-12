package com.ruoyi.suyuan.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备管理对象 device_info
 * 
 * @author ruoyi
 * @date 2025-05-12
 */
public class MonitorInfo
{
    private String deviceId;


    private String datas;

    private String date;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MonitorInfo{" +
                "deviceId='" + deviceId + '\'' +
                ", datas='" + datas + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
