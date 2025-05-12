package com.ruoyi.suyuan.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 视频设备管理对象 device_voice
 * 
 * @author ruoyi
 * @date 2025-05-12
 */
public class DeviceVoice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String voiceDeviceSerial;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String voiceUrl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setVoiceDeviceSerial(String voiceDeviceSerial) 
    {
        this.voiceDeviceSerial = voiceDeviceSerial;
    }

    public String getVoiceDeviceSerial() 
    {
        return voiceDeviceSerial;
    }

    public void setVoiceUrl(String voiceUrl) 
    {
        this.voiceUrl = voiceUrl;
    }

    public String getVoiceUrl() 
    {
        return voiceUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("voiceDeviceSerial", getVoiceDeviceSerial())
            .append("voiceUrl", getVoiceUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
