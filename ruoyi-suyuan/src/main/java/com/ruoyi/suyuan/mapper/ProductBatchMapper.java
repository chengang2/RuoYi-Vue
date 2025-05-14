package com.ruoyi.suyuan.mapper;

import java.util.List;
import com.ruoyi.suyuan.domain.ProductBatch;
import com.ruoyi.suyuan.domain.ProductBatchList;

/**
 * 产品批次管理Mapper接口
 * 
 * @author chengang
 * @date 2025-05-14
 */
public interface ProductBatchMapper 
{
    /**
     * 查询产品批次管理
     * 
     * @param id 产品批次管理主键
     * @return 产品批次管理
     */
    public ProductBatch selectProductBatchById(Integer id);

    public ProductBatch selectProductBatch(ProductBatch productBatch);

    /**
     * 查询产品批次管理列表
     * 
     * @param productBatchList 产品批次管理
     * @return 产品批次管理集合
     */
    public List<ProductBatch> selectProductBatchList(ProductBatchList productBatchList);

    /**
     * 新增产品批次管理
     * 
     * @param productBatch 产品批次管理
     * @return 结果
     */
    public int insertProductBatch(ProductBatch productBatch);

    /**
     * 修改产品批次管理
     * 
     * @param productBatch 产品批次管理
     * @return 结果
     */
    public int updateProductBatch(ProductBatch productBatch);

    /**
     * 删除产品批次管理
     * 
     * @param id 产品批次管理主键
     * @return 结果
     */
    public int deleteProductBatchById(Integer id);

    /**
     * 批量删除产品批次管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductBatchByIds(Integer[] ids);
}
