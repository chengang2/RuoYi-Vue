package com.ruoyi.suyuan.mapper;

import java.util.List;
import com.ruoyi.suyuan.domain.ProductCategory;

/**
 * 产品类别管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
public interface ProductCategoryMapper 
{
    /**
     * 查询产品类别管理
     * 
     * @param id 产品类别管理主键
     * @return 产品类别管理
     */
    public ProductCategory selectProductCategoryById(Integer id);

    public ProductCategory selectProductCategory(ProductCategory productCategory);
    /**
     * 查询产品类别管理列表
     * 
     * @param productCategory 产品类别管理
     * @return 产品类别管理集合
     */
    public List<ProductCategory> selectProductCategoryList(ProductCategory productCategory);

    /**
     * 新增产品类别管理
     * 
     * @param productCategory 产品类别管理
     * @return 结果
     */
    public int insertProductCategory(ProductCategory productCategory);

    /**
     * 修改产品类别管理
     * 
     * @param productCategory 产品类别管理
     * @return 结果
     */
    public int updateProductCategory(ProductCategory productCategory);

    /**
     * 删除产品类别管理
     * 
     * @param id 产品类别管理主键
     * @return 结果
     */
    public int deleteProductCategoryById(Integer id);

    /**
     * 批量删除产品类别管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductCategoryByIds(Integer[] ids);
}
