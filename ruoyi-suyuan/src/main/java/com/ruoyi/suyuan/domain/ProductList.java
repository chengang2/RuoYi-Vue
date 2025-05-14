package com.ruoyi.suyuan.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 产品管理对象 product
 * 
 * @author chengang
 * @date 2025-05-14
 */
public class ProductList extends BaseEntity
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
    private String standardName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer productCategoryId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String standardNo;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String specification;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer orginEnterpriseId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer factoryEnterpriseId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer produceProcessId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer growthProcessId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String photos;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String certificationIds;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String video;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String description;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String standardDescription;

    /** 食用方法 */
    @Excel(name = "食用方法")
    private String edibleMethod;

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

    public void setStandardName(String standardName) 
    {
        this.standardName = standardName;
    }

    public String getStandardName() 
    {
        return standardName;
    }

    public void setProductCategoryId(Integer productCategoryId) 
    {
        this.productCategoryId = productCategoryId;
    }

    public Integer getProductCategoryId() 
    {
        return productCategoryId;
    }

    public void setStandardNo(String standardNo) 
    {
        this.standardNo = standardNo;
    }

    public String getStandardNo() 
    {
        return standardNo;
    }

    public void setSpecification(String specification) 
    {
        this.specification = specification;
    }

    public String getSpecification() 
    {
        return specification;
    }

    public void setOrginEnterpriseId(Integer orginEnterpriseId) 
    {
        this.orginEnterpriseId = orginEnterpriseId;
    }

    public Integer getOrginEnterpriseId() 
    {
        return orginEnterpriseId;
    }

    public void setFactoryEnterpriseId(Integer factoryEnterpriseId) 
    {
        this.factoryEnterpriseId = factoryEnterpriseId;
    }

    public Integer getFactoryEnterpriseId() 
    {
        return factoryEnterpriseId;
    }

    public void setProduceProcessId(Integer produceProcessId) 
    {
        this.produceProcessId = produceProcessId;
    }

    public Integer getProduceProcessId() 
    {
        return produceProcessId;
    }

    public void setGrowthProcessId(Integer growthProcessId) 
    {
        this.growthProcessId = growthProcessId;
    }

    public Integer getGrowthProcessId() 
    {
        return growthProcessId;
    }

    public void setPhotos(String photos) 
    {
        this.photos = photos;
    }

    public String getPhotos() 
    {
        return photos;
    }

    public void setCertificationIds(String certificationIds) 
    {
        this.certificationIds = certificationIds;
    }

    public String getCertificationIds() 
    {
        return certificationIds;
    }

    public void setVideo(String video) 
    {
        this.video = video;
    }

    public String getVideo() 
    {
        return video;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setStandardDescription(String standardDescription) 
    {
        this.standardDescription = standardDescription;
    }

    public String getStandardDescription() 
    {
        return standardDescription;
    }

    public void setEdibleMethod(String edibleMethod) 
    {
        this.edibleMethod = edibleMethod;
    }

    public String getEdibleMethod() 
    {
        return edibleMethod;
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
            .append("standardName", getStandardName())
            .append("productCategoryId", getProductCategoryId())
            .append("standardNo", getStandardNo())
            .append("specification", getSpecification())
            .append("orginEnterpriseId", getOrginEnterpriseId())
            .append("factoryEnterpriseId", getFactoryEnterpriseId())
            .append("produceProcessId", getProduceProcessId())
            .append("growthProcessId", getGrowthProcessId())
            .append("photos", getPhotos())
            .append("certificationIds", getCertificationIds())
            .append("video", getVideo())
            .append("description", getDescription())
            .append("standardDescription", getStandardDescription())
            .append("edibleMethod", getEdibleMethod())
            .append("enterpriseIds", getEnterpriseIds())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
