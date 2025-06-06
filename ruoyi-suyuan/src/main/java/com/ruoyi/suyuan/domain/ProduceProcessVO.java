package com.ruoyi.suyuan.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 生产过程管理对象 produceprocess
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
public class ProduceProcessVO extends BaseEntity
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

    private ProduceProcessStep[] items;

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

    public ProduceProcessStep[] getItems() {
        return items;
    }
    public void setItems(ProduceProcessStep[] items) {
        this.items = items;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("enterpriseId", getEnterpriseId())
            .append("productCategoryId", getProductCategoryId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("items", getItems())
            .toString();
    }
}
