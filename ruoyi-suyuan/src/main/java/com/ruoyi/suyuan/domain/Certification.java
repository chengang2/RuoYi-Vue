package com.ruoyi.suyuan.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 certification
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
public class Certification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "认证名称")
    private String name;

    /** $column.columnComment */
    @Excel(name = "企业id")
    private Long enterpriseId;

    /** $column.columnComment */
    @Excel(name = "logo")
    private String logo;

    /** $column.columnComment */
    @Excel(name = "执照")
    private String license;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public void setLogo(String logo)
    {
        this.logo = logo;
    }

    public String getLogo() 
    {
        return logo;
    }

    public void setLicense(String license) 
    {
        this.license = license;
    }

    public String getLicense() 
    {
        return license;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("enterpriseId", getEnterpriseId())
            .append("logo", getLogo())
            .append("license", getLicense())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
