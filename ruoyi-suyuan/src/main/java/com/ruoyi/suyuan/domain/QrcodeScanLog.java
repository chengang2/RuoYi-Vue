package com.ruoyi.suyuan.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 二维码扫描日志管理对象 qrcodescanlog
 * 
 * @author chengang
 * @date 2025-05-15
 */
public class QrcodeScanLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 产品id */
    @Excel(name = "产品id")
    private Integer productId;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String cip;

    /** 所在城市id */
    @Excel(name = "所在城市id")
    private String cid;

    /** 所在城市名称 */
    @Excel(name = "所在城市名称")
    private String cname;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }

    public void setProductId(Integer productId) 
    {
        this.productId = productId;
    }

    public Integer getProductId() 
    {
        return productId;
    }

    public void setCip(String cip) 
    {
        this.cip = cip;
    }

    public String getCip() 
    {
        return cip;
    }

    public void setCid(String cid) 
    {
        this.cid = cid;
    }

    public String getCid() 
    {
        return cid;
    }

    public void setCname(String cname) 
    {
        this.cname = cname;
    }

    public String getCname() 
    {
        return cname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productId", getProductId())
            .append("cip", getCip())
            .append("cid", getCid())
            .append("cname", getCname())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
