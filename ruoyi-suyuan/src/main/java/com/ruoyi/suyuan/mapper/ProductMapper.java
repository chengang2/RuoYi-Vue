package com.ruoyi.suyuan.mapper;

import java.util.List;
import com.ruoyi.suyuan.domain.Product;
import com.ruoyi.suyuan.domain.ProductList;

/**
 * 产品管理Mapper接口
 * 
 * @author chengang
 * @date 2025-05-14
 */
public interface ProductMapper 
{
    /**
     * 查询产品管理
     * 
     * @param id 产品管理主键
     * @return 产品管理
     */
    public Product selectProductById(Integer id);

    public Product selectProduct(Product product);
    /**
     * 查询产品管理列表
     * 
     * @param productList 产品管理
     * @return 产品管理集合
     */
    public List<Product> selectProductList(ProductList productList);

    /**
     * 新增产品管理
     * 
     * @param product 产品管理
     * @return 结果
     */
    public int insertProduct(Product product);

    /**
     * 修改产品管理
     * 
     * @param product 产品管理
     * @return 结果
     */
    public int updateProduct(Product product);

    /**
     * 删除产品管理
     * 
     * @param id 产品管理主键
     * @return 结果
     */
    public int deleteProductById(Integer id);

    /**
     * 批量删除产品管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductByIds(Integer[] ids);
}
