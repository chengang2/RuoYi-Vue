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
public class DeviceInfoVO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "设备地址", readConverterExp = "$column.readConverterExp()")
    private String deviceAddress;

    /** $column.columnComment */
    @Excel(name = "企业id", readConverterExp = "$column.readConverterExp()")
    private Integer enterpriseId;

    /** 环境设备号,逗号分隔 */
    @Excel(name = "环境设备号,逗号分隔")
    private String monitorDeviceIds;

    /** 视频设备号,逗号分隔 */
    @Excel(name = "视频设备号,逗号分隔")
    private String voiceDeviceIds;

    private String monitorDeviceId;

    private String voiceDeviceId;

    /** 金纬度 */
    @Excel(name = "金纬度")
    private String latLon;

    /** 1:正常，2：异常 */
    @Excel(name = "1:正常，2：异常")
    private Integer status;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setDeviceAddress(String deviceAddress) 
    {
        this.deviceAddress = deviceAddress;
    }

    public String getDeviceAddress() 
    {
        return deviceAddress;
    }

    public void setEnterpriseId(Integer enterpriseId)
    {
        this.enterpriseId = enterpriseId;
    }

    public Integer getEnterpriseId()
    {
        return enterpriseId;
    }

    public void setMonitorDeviceIds(String monitorDeviceIds) 
    {
        this.monitorDeviceIds = monitorDeviceIds;
    }

    public String getMonitorDeviceIds() 
    {
        return monitorDeviceIds;
    }

    public void setVoiceDeviceIds(String voiceDeviceIds) 
    {
        this.voiceDeviceIds = voiceDeviceIds;
    }

    public String getVoiceDeviceIds() 
    {
        return voiceDeviceIds;
    }

    public void setLatLon(String latLon) 
    {
        this.latLon = latLon;
    }

    public String getLatLon() 
    {
        return latLon;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    public String getMonitorDeviceId() {
        return monitorDeviceId;
    }

    public void setMonitorDeviceId(String monitorDeviceId) {
        this.monitorDeviceId = monitorDeviceId;
    }

    public String getVoiceDeviceId() {
        return voiceDeviceId;
    }

    public void setVoiceDeviceId(String voiceDeviceId) {
        this.voiceDeviceId = voiceDeviceId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceAddress", getDeviceAddress())
            .append("enterpriseId", getEnterpriseId())
            .append("monitorDeviceIds", getMonitorDeviceIds())
            .append("voiceDeviceIds", getVoiceDeviceIds())
            .append("latLon", getLatLon())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
