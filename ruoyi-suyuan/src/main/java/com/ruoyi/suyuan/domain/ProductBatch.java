package com.ruoyi.suyuan.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品批次管理对象 productbatch
 * 
 * @author chengang
 * @date 2025-05-14
 */
public class ProductBatch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date produceDatetime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String batchNumber;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer productId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String testReport;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String testReportPicture;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String qrCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer printNum;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String md5Code;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer status;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }

    public void setProduceDatetime(Date produceDatetime) 
    {
        this.produceDatetime = produceDatetime;
    }

    public Date getProduceDatetime() 
    {
        return produceDatetime;
    }

    public void setBatchNumber(String batchNumber) 
    {
        this.batchNumber = batchNumber;
    }

    public String getBatchNumber() 
    {
        return batchNumber;
    }

    public void setProductId(Integer productId) 
    {
        this.productId = productId;
    }

    public Integer getProductId() 
    {
        return productId;
    }

    public void setTestReport(String testReport) 
    {
        this.testReport = testReport;
    }

    public String getTestReport() 
    {
        return testReport;
    }

    public void setTestReportPicture(String testReportPicture) 
    {
        this.testReportPicture = testReportPicture;
    }

    public String getTestReportPicture() 
    {
        return testReportPicture;
    }

    public void setQrCode(String qrCode) 
    {
        this.qrCode = qrCode;
    }

    public String getQrCode() 
    {
        return qrCode;
    }

    public void setPrintNum(Integer printNum) 
    {
        this.printNum = printNum;
    }

    public Integer getPrintNum() 
    {
        return printNum;
    }

    public void setMd5Code(String md5Code) 
    {
        this.md5Code = md5Code;
    }

    public String getMd5Code() 
    {
        return md5Code;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("produceDatetime", getProduceDatetime())
            .append("batchNumber", getBatchNumber())
            .append("productId", getProductId())
            .append("testReport", getTestReport())
            .append("testReportPicture", getTestReportPicture())
            .append("qrCode", getQrCode())
            .append("printNum", getPrintNum())
            .append("md5Code", getMd5Code())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
