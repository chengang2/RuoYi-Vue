package com.ruoyi.suyuan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.suyuan.domain.Enterprise;
import com.ruoyi.suyuan.service.IEnterpriseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.suyuan.domain.ProductCategory;
import com.ruoyi.suyuan.service.IProductCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品类别管理Controller
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
@RestController
@RequestMapping("/suyuan/productcategory")
public class ProductCategoryController extends BaseController
{
    @Autowired
    private IProductCategoryService productCategoryService;
    @Autowired
    private IEnterpriseService enterpriseService;

    /**
     * 查询产品类别管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:productcategory:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductCategory productCategory)
    {

        Map<String,Object> data = new HashMap<>();
        List<ProductCategory> list = productCategoryService.selectProductCategoryList(productCategory);
        startPage();
        List<Map<String,Object>> items = new ArrayList<>();
        for (ProductCategory productCategory1 : list) {
            Map<String,Object> item = new HashMap<>();
            Integer enterpriseId = productCategory1.getEnterpriseId();
            String enterpriseName = "";
            if (enterpriseId != null) {
                Enterprise enterprise = enterpriseService.selectEnterpriseById(enterpriseId);
                enterpriseName = enterprise.getName();
            }
            item.put("id", productCategory1.getId());
            item.put("enterpriseName",enterpriseName);
            item.put("name", productCategory1.getName());
            item.put("enterpriseId", productCategory1.getEnterpriseId());
            item.put("createTime", productCategory1.getCreateTime());
            item.put("updateTime", productCategory1.getUpdateTime());

            items.add(item);
        }

        return getDataTable(items);
    }
    @PreAuthorize("@ss.hasPermi('suyuan:productcategory:list')")
    @GetMapping("/getAll")
    public AjaxResult getAll(ProductCategory productCategory)
    {
        Map<String,Object> data = new HashMap<>();
        List<ProductCategory> list = productCategoryService.selectProductCategoryList(productCategory);
        List<Map<String,Object>> items = new ArrayList<>();
        for (ProductCategory productCategory1 : list) {
            Map<String,Object> item = new HashMap<>();
            Integer enterpriseId = productCategory1.getEnterpriseId();
            String enterpriseName = "";
            if (enterpriseId != null) {
                Enterprise enterprise = enterpriseService.selectEnterpriseById(enterpriseId);
                enterpriseName = enterprise.getName();
            }

            item.put("id", productCategory1.getId());
            item.put("enterpriseName",enterpriseName);
            item.put("name", productCategory1.getName());
            item.put("enterpriseId", productCategory1.getEnterpriseId());
            item.put("createTime", productCategory1.getCreateTime());
            item.put("updateTime", productCategory1.getUpdateTime());

            items.add(item);
        }

        data.put("items", items);

        return success(data);
    }

    ///**
    // * 导出产品类别管理列表
    // */
    //@PreAuthorize("@ss.hasPermi('suyuan:productcategory:export')")
    //@Log(title = "产品类别管理", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //public void export(HttpServletResponse response, ProductCategory productCategory)
    //{
    //    List<ProductCategory> list = productCategoryService.selectProductCategoryList(productCategory);
    //    ExcelUtil<ProductCategory> util = new ExcelUtil<ProductCategory>(ProductCategory.class);
    //    util.exportExcel(response, list, "产品类别管理数据");
    //}

    /**
     * 获取产品类别管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('suyuan:productcategory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        Map<String,Object> data = new HashMap<>();
        ProductCategory productCategory = productCategoryService.selectProductCategoryById(id);
        Integer enterpriseId = productCategory.getEnterpriseId();
        if (enterpriseId == null) {
            return  error("企业id不能为空");
        }
        Enterprise enterprise = enterpriseService.selectEnterpriseById(enterpriseId);
        data.put("id", productCategory.getId());
        data.put("enterpriseName",enterprise.getName());
        data.put("name", productCategory.getName());
        data.put("enterpriseId", productCategory.getEnterpriseId());
        data.put("createTime", productCategory.getCreateTime());
        data.put("updateTime", productCategory.getUpdateTime());
        return success(data);
    }

    /**
     * 新增产品类别管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:productcategory:add')")
    @Log(title = "产品类别管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductCategory productCategory)
    {
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setName(productCategory.getName());
        productCategory1.setEnterpriseId(productCategory.getEnterpriseId());
        ProductCategory productCategory2 = productCategoryService.selectProductCategory(productCategory1);
        if (productCategory2 != null) {
            return error("品类名称已存在,请更改");
        }

        return toAjax(productCategoryService.insertProductCategory(productCategory));
    }

    /**
     * 修改产品类别管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:productcategory:edit')")
    @Log(title = "产品类别管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductCategory productCategory)
    {
        return toAjax(productCategoryService.updateProductCategory(productCategory));
    }

    /**
     * 删除产品类别管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:productcategory:remove')")
    @Log(title = "产品类别管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(productCategoryService.deleteProductCategoryByIds(ids));
    }
}
