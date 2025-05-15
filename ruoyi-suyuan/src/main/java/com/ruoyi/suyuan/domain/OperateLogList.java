package com.ruoyi.suyuan.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 操作日志管理对象 operatelog
 * 
 * @author chengang
 * @date 2025-05-15
 */
public class OperateLogList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 产品id */
    @Excel(name = "产品id")
    private Integer productId;

    /** ip地址 */
    @Excel(name = "ip地址")
    private String cip;

    /** 城市编码 */
    @Excel(name = "城市编码")
    private String cid;

    /** 城市名称 */
    @Excel(name = "城市名称")
    private String cname;

    /** 提交的 评分 1-5分 5分最高，4分以上算好评 */
    @Excel(name = "提交的 评分 1-5分 5分最高，4分以上算好评")
    private Integer submitScore;

    /** 提交的评论 */
    @Excel(name = "提交的评论")
    private String submitComment;

    private List<Integer> productIds;

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

    public void setSubmitScore(Integer submitScore)
    {
        this.submitScore = submitScore;
    }

    public Integer getSubmitScore()
    {
        return submitScore;
    }

    public void setSubmitComment(String submitComment)
    {
        this.submitComment = submitComment;
    }

    public String getSubmitComment()
    {
        return submitComment;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }
    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productId", getProductId())
            .append("cip", getCip())
            .append("cid", getCid())
            .append("cname", getCname())
            .append("submitScore", getSubmitScore())
            .append("submitComment", getSubmitComment())
            .append("productIds", getProductIds())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
