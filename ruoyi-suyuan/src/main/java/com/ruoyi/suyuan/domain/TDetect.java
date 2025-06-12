package com.ruoyi.suyuan.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检测管理对象 t_detect
 * 
 * @author ruoyi
 * @date 2025-06-12
 */
public class TDetect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 采样表id */
    @Excel(name = "采样表id")
    private Long sampleId;

    /** 检测单编号 */
    @Excel(name = "检测单编号")
    private String detectNo;

    /** 采样检测结果：
            1：合格
            2：不合格
            3：未检测 */
    @Excel(name = "采样检测结果：1：合格, 2：不合格, 3：未检测")
    private String inspectResult;

    /** 采样设备编号 */
    @Excel(name = "采样设备编号")
    private String sampleDeviceId;

    /** 检测项目 */
    @Excel(name = "检测项目")
    private String detectItem;

    /** 检测类别 */
    @Excel(name = "检测类别")
    private String detectType;

    /** 检测方法 */
    @Excel(name = "检测方法")
    private String detectMethod;

    /** 检测员 */
    @Excel(name = "检测员")
    private String inspector;

    /** 检测标准 */
    @Excel(name = "检测标准")
    private String detectCriteria;

    /** 检测时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检测时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date detectTime;

    /** 检测地点 */
    @Excel(name = "检测地点")
    private String detectLocation;

    /** 市场 */
    @Excel(name = "市场")
    private String marketCode;

    /** 检测灵敏度 */
    @Excel(name = "检测灵敏度")
    private String detectSensivity;

    /** 检测试剂品牌 */
    @Excel(name = "检测试剂品牌")
    private String reagent;

    /** 1 阴性
            2 疑似阳性
            3 确认阳性 */
    @Excel(name = "1 阴性,2 疑似阳性,3 确认阳性")
    private String detectResult;

    /** 单位：分钟 */
    @Excel(name = "单位：分钟")
    private String handleDuration;

    /** 1:已发布，0：未发布 */
    @Excel(name = "1:已发布，0：未发布")
    private Integer publishStatus;

    /** 实时检测结果 */
    @Excel(name = "实时检测结果")
    private String realDetectResult;

    /** 短信发送状态：1：已发送；0：未发送 */
    @Excel(name = "短信发送状态：1：已发送；0：未发送")
    private Integer sendStatus;

    /** 上传检测值 */
    @Excel(name = "上传检测值")
    private String testValue;

    /** 检测设备编号 */
    @Excel(name = "检测设备编号")
    private String testDeviceId;

    /** 阳性处理：已处置，未处置 */
    @Excel(name = "阳性处理：已处置，未处置")
    private String handingStatus;

    /** 不合格产品处置办法 */
    @Excel(name = "不合格产品处置办法")
    private String reDetectDisposal;

    /** 检测单位 */
    @Excel(name = "检测单位")
    private String detectUnit;

    /** 状态 */
    @Excel(name = "状态")
    private Integer state;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setSampleId(Long sampleId) 
    {
        this.sampleId = sampleId;
    }

    public Long getSampleId() 
    {
        return sampleId;
    }

    public void setDetectNo(String detectNo) 
    {
        this.detectNo = detectNo;
    }

    public String getDetectNo() 
    {
        return detectNo;
    }

    public void setInspectResult(String inspectResult) 
    {
        this.inspectResult = inspectResult;
    }

    public String getInspectResult() 
    {
        return inspectResult;
    }

    public void setSampleDeviceId(String sampleDeviceId) 
    {
        this.sampleDeviceId = sampleDeviceId;
    }

    public String getSampleDeviceId() 
    {
        return sampleDeviceId;
    }

    public void setDetectItem(String detectItem) 
    {
        this.detectItem = detectItem;
    }

    public String getDetectItem() 
    {
        return detectItem;
    }

    public void setDetectType(String detectType) 
    {
        this.detectType = detectType;
    }

    public String getDetectType() 
    {
        return detectType;
    }

    public void setDetectMethod(String detectMethod) 
    {
        this.detectMethod = detectMethod;
    }

    public String getDetectMethod() 
    {
        return detectMethod;
    }

    public void setInspector(String inspector) 
    {
        this.inspector = inspector;
    }

    public String getInspector() 
    {
        return inspector;
    }

    public void setDetectCriteria(String detectCriteria) 
    {
        this.detectCriteria = detectCriteria;
    }

    public String getDetectCriteria() 
    {
        return detectCriteria;
    }

    public void setDetectTime(Date detectTime) 
    {
        this.detectTime = detectTime;
    }

    public Date getDetectTime() 
    {
        return detectTime;
    }

    public void setDetectLocation(String detectLocation) 
    {
        this.detectLocation = detectLocation;
    }

    public String getDetectLocation() 
    {
        return detectLocation;
    }

    public void setMarketCode(String marketCode) 
    {
        this.marketCode = marketCode;
    }

    public String getMarketCode() 
    {
        return marketCode;
    }

    public void setDetectSensivity(String detectSensivity) 
    {
        this.detectSensivity = detectSensivity;
    }

    public String getDetectSensivity() 
    {
        return detectSensivity;
    }

    public void setReagent(String reagent) 
    {
        this.reagent = reagent;
    }

    public String getReagent() 
    {
        return reagent;
    }

    public void setDetectResult(String detectResult) 
    {
        this.detectResult = detectResult;
    }

    public String getDetectResult() 
    {
        return detectResult;
    }

    public void setHandleDuration(String handleDuration) 
    {
        this.handleDuration = handleDuration;
    }

    public String getHandleDuration() 
    {
        return handleDuration;
    }

    public void setPublishStatus(Integer publishStatus) 
    {
        this.publishStatus = publishStatus;
    }

    public Integer getPublishStatus() 
    {
        return publishStatus;
    }

    public void setRealDetectResult(String realDetectResult) 
    {
        this.realDetectResult = realDetectResult;
    }

    public String getRealDetectResult() 
    {
        return realDetectResult;
    }

    public void setSendStatus(Integer sendStatus) 
    {
        this.sendStatus = sendStatus;
    }

    public Integer getSendStatus() 
    {
        return sendStatus;
    }

    public void setTestValue(String testValue) 
    {
        this.testValue = testValue;
    }

    public String getTestValue() 
    {
        return testValue;
    }

    public void setTestDeviceId(String testDeviceId) 
    {
        this.testDeviceId = testDeviceId;
    }

    public String getTestDeviceId() 
    {
        return testDeviceId;
    }

    public void setHandingStatus(String handingStatus) 
    {
        this.handingStatus = handingStatus;
    }

    public String getHandingStatus() 
    {
        return handingStatus;
    }

    public void setReDetectDisposal(String reDetectDisposal) 
    {
        this.reDetectDisposal = reDetectDisposal;
    }

    public String getReDetectDisposal() 
    {
        return reDetectDisposal;
    }

    public void setDetectUnit(String detectUnit) 
    {
        this.detectUnit = detectUnit;
    }

    public String getDetectUnit() 
    {
        return detectUnit;
    }

    public void setState(Integer state) 
    {
        this.state = state;
    }

    public Integer getState() 
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sampleId", getSampleId())
            .append("detectNo", getDetectNo())
            .append("inspectResult", getInspectResult())
            .append("sampleDeviceId", getSampleDeviceId())
            .append("detectItem", getDetectItem())
            .append("detectType", getDetectType())
            .append("detectMethod", getDetectMethod())
            .append("inspector", getInspector())
            .append("detectCriteria", getDetectCriteria())
            .append("detectTime", getDetectTime())
            .append("detectLocation", getDetectLocation())
            .append("marketCode", getMarketCode())
            .append("detectSensivity", getDetectSensivity())
            .append("reagent", getReagent())
            .append("detectResult", getDetectResult())
            .append("handleDuration", getHandleDuration())
            .append("publishStatus", getPublishStatus())
            .append("updateTime", getUpdateTime())
            .append("realDetectResult", getRealDetectResult())
            .append("sendStatus", getSendStatus())
            .append("testValue", getTestValue())
            .append("testDeviceId", getTestDeviceId())
            .append("createTime", getCreateTime())
            .append("handingStatus", getHandingStatus())
            .append("reDetectDisposal", getReDetectDisposal())
            .append("detectUnit", getDetectUnit())
            .append("state", getState())
            .toString();
    }
}
