package com.ruoyi.suyuan.domain;

public class DeviceListVO {
    private Integer id;
    private Integer enterpriseId;
    private String enterpriseName;
    private String deviceAddress;
    private String monitorDeviceIds;
    private String voiceDeviceIds;
    private Integer status;
    private String latLon;
    private String createTime;
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public String getMonitorDeviceIds() {
        return monitorDeviceIds;
    }

    public void setMonitorDeviceIds(String monitorDeviceIds) {
        this.monitorDeviceIds = monitorDeviceIds;
    }

    public String getVoiceDeviceIds() {
        return voiceDeviceIds;
    }

    public void setVoiceDeviceIds(String voiceDeviceIds) {
        this.voiceDeviceIds = voiceDeviceIds;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLatLon() {
        return latLon;
    }

    public void setLatLon(String latLon) {
        this.latLon = latLon;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "DeviceListVO{" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", deviceAddress='" + deviceAddress + '\'' +
                ", monitorDeviceIds='" + monitorDeviceIds + '\'' +
                ", voiceDeviceIds='" + voiceDeviceIds + '\'' +
                ", status='" + status + '\'' +
                ", latLon='" + latLon + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
