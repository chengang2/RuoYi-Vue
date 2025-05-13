package com.ruoyi.suyuan.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 成长过程管理对象 growthprocess
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
public class GrowthProcessList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer enterpriseId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer productCategoryId;

    private List<Integer> enterpriseIds;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
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

    public void setEnterpriseId(Integer enterpriseId) 
    {
        this.enterpriseId = enterpriseId;
    }

    public Integer getEnterpriseId() 
    {
        return enterpriseId;
    }

    public void setProductCategoryId(Integer productCategoryId) 
    {
        this.productCategoryId = productCategoryId;
    }

    public Integer getProductCategoryId() 
    {
        return productCategoryId;
    }

    public List<Integer> getEnterpriseIds() {
        return enterpriseIds;
    }
    public void setEnterpriseIds(List<Integer> enterpriseIds) {
        this.enterpriseIds = enterpriseIds;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("enterpriseId", getEnterpriseId())
            .append("productCategoryId", getProductCategoryId())
            .append("enterpriseIds", getEnterpriseIds())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
