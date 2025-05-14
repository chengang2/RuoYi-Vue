package com.ruoyi.suyuan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.suyuan.domain.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.suyuan.mapper.ProductMapper;
import com.ruoyi.suyuan.domain.Product;
import com.ruoyi.suyuan.service.IProductService;

/**
 * 产品管理Service业务层处理
 * 
 * @author chengang
 * @date 2025-05-14
 */
@Service
public class ProductServiceImpl implements IProductService 
{
    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询产品管理
     * 
     * @param id 产品管理主键
     * @return 产品管理
     */
    @Override
    public Product selectProductById(Integer id)
    {
        return productMapper.selectProductById(id);
    }

    @Override
    public Product selectProduct(Product product) {
        return productMapper.selectProduct(product);
    }

    /**
     * 查询产品管理列表
     * 
     * @param productList 产品管理
     * @return 产品管理
     */
    @Override
    public List<Product> selectProductList(ProductList productList)
    {
        return productMapper.selectProductList(productList);
    }

    /**
     * 新增产品管理
     * 
     * @param product 产品管理
     * @return 结果
     */
    @Override
    public int insertProduct(Product product)
    {
        product.setCreateTime(DateUtils.getNowDate());
        return productMapper.insertProduct(product);
    }

    /**
     * 修改产品管理
     * 
     * @param product 产品管理
     * @return 结果
     */
    @Override
    public int updateProduct(Product product)
    {
        product.setUpdateTime(DateUtils.getNowDate());
        return productMapper.updateProduct(product);
    }

    /**
     * 批量删除产品管理
     * 
     * @param ids 需要删除的产品管理主键
     * @return 结果
     */
    @Override
    public int deleteProductByIds(Integer[] ids)
    {
        return productMapper.deleteProductByIds(ids);
    }

    /**
     * 删除产品管理信息
     * 
     * @param id 产品管理主键
     * @return 结果
     */
    @Override
    public int deleteProductById(Integer id)
    {
        return productMapper.deleteProductById(id);
    }
}
