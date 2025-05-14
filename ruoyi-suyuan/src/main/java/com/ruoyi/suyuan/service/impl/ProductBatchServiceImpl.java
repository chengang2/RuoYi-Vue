package com.ruoyi.suyuan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.suyuan.domain.ProductBatchList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.suyuan.mapper.ProductBatchMapper;
import com.ruoyi.suyuan.domain.ProductBatch;
import com.ruoyi.suyuan.service.IProductBatchService;

/**
 * 产品批次管理Service业务层处理
 * 
 * @author chengang
 * @date 2025-05-14
 */
@Service
public class ProductBatchServiceImpl implements IProductBatchService 
{
    @Autowired
    private ProductBatchMapper productBatchMapper;

    /**
     * 查询产品批次管理
     * 
     * @param id 产品批次管理主键
     * @return 产品批次管理
     */
    @Override
    public ProductBatch selectProductBatchById(Integer id)
    {
        return productBatchMapper.selectProductBatchById(id);
    }

    @Override
    public ProductBatch selectProductBatch(ProductBatch productBatch) {
        return productBatchMapper.selectProductBatch(productBatch);
    }

    /**
     * 查询产品批次管理列表
     * 
     * @param productBatchList 产品批次管理
     * @return 产品批次管理
     */
    @Override
    public List<ProductBatch> selectProductBatchList(ProductBatchList productBatchList)
    {
        return productBatchMapper.selectProductBatchList(productBatchList);
    }

    /**
     * 新增产品批次管理
     * 
     * @param productBatch 产品批次管理
     * @return 结果
     */
    @Override
    public int insertProductBatch(ProductBatch productBatch)
    {
        productBatch.setCreateTime(DateUtils.getNowDate());
        return productBatchMapper.insertProductBatch(productBatch);
    }

    /**
     * 修改产品批次管理
     * 
     * @param productBatch 产品批次管理
     * @return 结果
     */
    @Override
    public int updateProductBatch(ProductBatch productBatch)
    {
        productBatch.setUpdateTime(DateUtils.getNowDate());
        return productBatchMapper.updateProductBatch(productBatch);
    }

    /**
     * 批量删除产品批次管理
     * 
     * @param ids 需要删除的产品批次管理主键
     * @return 结果
     */
    @Override
    public int deleteProductBatchByIds(Integer[] ids)
    {
        return productBatchMapper.deleteProductBatchByIds(ids);
    }

    /**
     * 删除产品批次管理信息
     * 
     * @param id 产品批次管理主键
     * @return 结果
     */
    @Override
    public int deleteProductBatchById(Integer id)
    {
        return productBatchMapper.deleteProductBatchById(id);
    }
}
