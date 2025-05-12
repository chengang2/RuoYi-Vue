package com.ruoyi.suyuan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.suyuan.mapper.ProductCategoryMapper;
import com.ruoyi.suyuan.domain.ProductCategory;
import com.ruoyi.suyuan.service.IProductCategoryService;

/**
 * 产品类别管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService 
{
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * 查询产品类别管理
     * 
     * @param id 产品类别管理主键
     * @return 产品类别管理
     */
    @Override
    public ProductCategory selectProductCategoryById(Integer id)
    {
        return productCategoryMapper.selectProductCategoryById(id);
    }

    @Override
    public ProductCategory selectProductCategory(ProductCategory productCategory) {
        return productCategoryMapper.selectProductCategory(productCategory);
    }

    /**
     * 查询产品类别管理列表
     * 
     * @param productCategory 产品类别管理
     * @return 产品类别管理
     */
    @Override
    public List<ProductCategory> selectProductCategoryList(ProductCategory productCategory)
    {
        return productCategoryMapper.selectProductCategoryList(productCategory);
    }

    /**
     * 新增产品类别管理
     * 
     * @param productCategory 产品类别管理
     * @return 结果
     */
    @Override
    public int insertProductCategory(ProductCategory productCategory)
    {
        productCategory.setCreateTime(DateUtils.getNowDate());
        return productCategoryMapper.insertProductCategory(productCategory);
    }

    /**
     * 修改产品类别管理
     * 
     * @param productCategory 产品类别管理
     * @return 结果
     */
    @Override
    public int updateProductCategory(ProductCategory productCategory)
    {
        productCategory.setUpdateTime(DateUtils.getNowDate());
        return productCategoryMapper.updateProductCategory(productCategory);
    }

    /**
     * 批量删除产品类别管理
     * 
     * @param ids 需要删除的产品类别管理主键
     * @return 结果
     */
    @Override
    public int deleteProductCategoryByIds(Integer[] ids)
    {
        return productCategoryMapper.deleteProductCategoryByIds(ids);
    }

    /**
     * 删除产品类别管理信息
     * 
     * @param id 产品类别管理主键
     * @return 结果
     */
    @Override
    public int deleteProductCategoryById(Integer id)
    {
        return productCategoryMapper.deleteProductCategoryById(id);
    }
}
