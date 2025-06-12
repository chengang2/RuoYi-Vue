package com.ruoyi.suyuan.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 * 样品管理对象 t_sample
 * 
 * @author ruoyi
 * @date 2025-06-12
 */
public class TSample extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 样品批次 */
    @Excel(name = "样品批次")
    private String sampleBatch;

    /** 样品编号 */
    @Excel(name = "样品编号")
    private String sampleNo;

    /** 任务来源 */
    @Excel(name = "任务来源")
    private String taskSource;

    /** 样品来源
            1 抽检
            2 送检 */
    @Excel(name = "样品来源,1 抽检,2 送检")
    private String sampleSource;

    /** 样品名称 */
    @Excel(name = "样品名称")
    private String sampleName;

    /** 采样价格 */
    @Excel(name = "采样价格")
    private String samplePrice;

    /** 采样人员编号 */
    @Excel(name = "采样人员编号")
    private Long samplerId;

    /** 区域 */
    @Excel(name = "区域")
    private String distirctCode;

    /** 市场 */
    @Excel(name = "市场")
    private String marketCode;

    /** 摊位 */
    @Excel(name = "摊位")
    private String booth;

    /** 样品来源 */
    @Excel(name = "样品来源")
    private String vendor;

    /** 采样时间 */
    @NotNull(message = "采样时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "采样时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date sampleTime;

    /** 样品种类 */
    @Excel(name = "样品种类")
    private String sampleCategory;

    /** 采样照片 */
    @Excel(name = "采样照片")
    private String samplePhoto;

    /** 采样视频 */
    @Excel(name = "采样视频")
    private String sampleVideo;

    /** 签名照片 */
    @Excel(name = "签名照片")
    private String signPhoto;

    /** 进货单 */
    @Excel(name = "进货单")
    private String purchasePhoto;

    /** 采样员 */
    @Excel(name = "采样员")
    private String sampler;

    /** 状态 */
    @Excel(name = "状态")
    private Integer state;

    /** 数量 */
    @Excel(name = "数量")
    private String sampleQuantity;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setSampleBatch(String sampleBatch) 
    {
        this.sampleBatch = sampleBatch;
    }

    public String getSampleBatch() 
    {
        return sampleBatch;
    }

    public void setSampleNo(String sampleNo) 
    {
        this.sampleNo = sampleNo;
    }

    public String getSampleNo() 
    {
        return sampleNo;
    }

    public void setTaskSource(String taskSource) 
    {
        this.taskSource = taskSource;
    }

    public String getTaskSource() 
    {
        return taskSource;
    }

    public void setSampleSource(String sampleSource) 
    {
        this.sampleSource = sampleSource;
    }

    public String getSampleSource() 
    {
        return sampleSource;
    }

    public void setSampleName(String sampleName) 
    {
        this.sampleName = sampleName;
    }

    public String getSampleName() 
    {
        return sampleName;
    }

    public void setSamplePrice(String samplePrice) 
    {
        this.samplePrice = samplePrice;
    }

    public String getSamplePrice() 
    {
        return samplePrice;
    }

    public void setSamplerId(Long samplerId) 
    {
        this.samplerId = samplerId;
    }

    public Long getSamplerId() 
    {
        return samplerId;
    }

    public void setDistirctCode(String distirctCode) 
    {
        this.distirctCode = distirctCode;
    }

    public String getDistirctCode() 
    {
        return distirctCode;
    }

    public void setMarketCode(String marketCode) 
    {
        this.marketCode = marketCode;
    }

    public String getMarketCode() 
    {
        return marketCode;
    }

    public void setBooth(String booth) 
    {
        this.booth = booth;
    }

    public String getBooth() 
    {
        return booth;
    }

    public void setVendor(String vendor) 
    {
        this.vendor = vendor;
    }

    public String getVendor() 
    {
        return vendor;
    }

    public void setSampleTime(Date sampleTime) 
    {
        this.sampleTime = sampleTime;
    }

    public Date getSampleTime() 
    {
        return sampleTime;
    }

    public void setSampleCategory(String sampleCategory) 
    {
        this.sampleCategory = sampleCategory;
    }

    public String getSampleCategory() 
    {
        return sampleCategory;
    }

    public void setSamplePhoto(String samplePhoto) 
    {
        this.samplePhoto = samplePhoto;
    }

    public String getSamplePhoto() 
    {
        return samplePhoto;
    }

    public void setSampleVideo(String sampleVideo) 
    {
        this.sampleVideo = sampleVideo;
    }

    public String getSampleVideo() 
    {
        return sampleVideo;
    }

    public void setSignPhoto(String signPhoto) 
    {
        this.signPhoto = signPhoto;
    }

    public String getSignPhoto() 
    {
        return signPhoto;
    }

    public void setPurchasePhoto(String purchasePhoto) 
    {
        this.purchasePhoto = purchasePhoto;
    }

    public String getPurchasePhoto() 
    {
        return purchasePhoto;
    }

    public void setSampler(String sampler) 
    {
        this.sampler = sampler;
    }

    public String getSampler() 
    {
        return sampler;
    }

    public void setState(Integer state) 
    {
        this.state = state;
    }

    public Integer getState() 
    {
        return state;
    }

    public void setSampleQuantity(String sampleQuantity) 
    {
        this.sampleQuantity = sampleQuantity;
    }

    public String getSampleQuantity() 
    {
        return sampleQuantity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sampleBatch", getSampleBatch())
            .append("sampleNo", getSampleNo())
            .append("taskSource", getTaskSource())
            .append("sampleSource", getSampleSource())
            .append("sampleName", getSampleName())
            .append("samplePrice", getSamplePrice())
            .append("samplerId", getSamplerId())
            .append("distirctCode", getDistirctCode())
            .append("marketCode", getMarketCode())
            .append("booth", getBooth())
            .append("vendor", getVendor())
            .append("sampleTime", getSampleTime())
            .append("sampleCategory", getSampleCategory())
            .append("samplePhoto", getSamplePhoto())
            .append("sampleVideo", getSampleVideo())
            .append("signPhoto", getSignPhoto())
            .append("purchasePhoto", getPurchasePhoto())
            .append("sampler", getSampler())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("state", getState())
            .append("sampleQuantity", getSampleQuantity())
            .append("remark", getRemark())
            .toString();
    }
}
