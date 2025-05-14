package com.ruoyi.suyuan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.suyuan.domain.*;
import com.ruoyi.suyuan.service.IEnterpriseService;
import com.ruoyi.suyuan.tools.DateFormatUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.suyuan.service.IProductService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品管理Controller
 * 
 * @author chengang
 * @date 2025-05-14
 */
@Api(tags = "溯源模块-产品管理")
@RestController
@RequestMapping("/suyuan/product")
public class ProductController extends BaseController
{
    @Autowired
    private IProductService productService;
    @Autowired
    private IEnterpriseService enterpriseService;

    /**
     * 查询产品管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:product:list')")
    @ApiOperation("获取产品列表,原接口: /product/getAllByPage")
    @GetMapping("/list")
    public TableDataInfo list(Product product)
    {
        Integer enterpriseId = product.getEnterpriseId();
        List<Integer> enterpriseIds = new ArrayList<>();
        List<Enterprise> enterprises = enterpriseService.selectEnterpriseByParentAndSelfId(enterpriseId);
        for (Enterprise enterprise : enterprises) {
            Integer id = enterprise.getId();
            enterpriseIds.add(id);
        }

        if(!enterpriseIds.isEmpty()){

            ProductList productList = new ProductList();
            productList.setEnterpriseIds(enterpriseIds);
            startPage();
            List<Product> products = productService.selectProductList(productList);

            PageInfo<Product> pageInfo = new PageInfo<>(products);

            List<Map<String,Object>> ProductLists = new ArrayList<>();
            for (Product p : products) {
                Map<String,Object> item = new HashMap<>();

                String enterpriseName = "";
                Integer eId = p.getEnterpriseId();
                if (eId != null) {
                    Enterprise e = enterpriseService.selectEnterpriseById(eId);
                    if (e != null) {
                        enterpriseName = e.getName();
                    }
                }

                item.put("id", p.getId());
                item.put("name", p.getName());
                item.put("enterpriseId", eId);
                item.put("enterpriseName", enterpriseName);
                item.put("standardName", p.getStandardName());
                item.put("productCategoryId", p.getProductCategoryId());
                item.put("standardNo", p.getStandardNo());
                item.put("specification",p.getSpecification());
                item.put("orginEnterpriseId",p.getOrginEnterpriseId());
                item.put("factoryEnterpriseId",p.getFactoryEnterpriseId());
                item.put("produceProcessId",p.getProduceProcessId());
                item.put("growthProcessId",p.getGrowthProcessId());
                item.put("certificationIds", p.getCertificationIds());
                item.put("video", p.getVideo());
                item.put("description", p.getDescription());
                item.put("standardDescription", p.getStandardDescription());
                item.put("edibleMethod", p.getEdibleMethod());
                item.put("photos", p.getPhotos());
                item.put("createTime", DateFormatUtil.format(p.getCreateTime()));
                item.put("updateTime", DateFormatUtil.format(p.getUpdateTime()));

                ProductLists.add(item);
            }

            // 用原始list的分页信息，返回你组装的items
            TableDataInfo rspData = new TableDataInfo();
            rspData.setCode(HttpStatus.SUCCESS);
            rspData.setMsg("查询成功");
            rspData.setRows(ProductLists);
            rspData.setTotal(pageInfo.getTotal());
            return rspData;

        }else{
            List<Map<String,Object>> ProductLists = new ArrayList<>();
            return getDataTable(ProductLists);
        }
    }

    @PreAuthorize("@ss.hasPermi('suyuan:product:list')")
    @ApiOperation("获取产品列表,原接口: /product/getAll")
    @GetMapping("/getAll")
    public AjaxResult getAll(Product product)
    {
        Map<String, Object> data = new HashMap<>();

        Integer enterpriseId = product.getEnterpriseId();
        List<Integer> enterpriseIds = new ArrayList<>();
        List<Enterprise> enterprises = enterpriseService.selectEnterpriseByParentAndSelfId(enterpriseId);
        for (Enterprise enterprise : enterprises) {
            Integer id = enterprise.getId();
            enterpriseIds.add(id);
        }

        if(!enterpriseIds.isEmpty()) {

            ProductList productList = new ProductList();
            productList.setEnterpriseIds(enterpriseIds);

            List<Product> products = productService.selectProductList(productList);

            List<Map<String, Object>> ProductLists = new ArrayList<>();
            for (Product p : products) {
                Map<String, Object> item = new HashMap<>();

                String enterpriseName = "";
                Integer eId = p.getEnterpriseId();
                if (eId != null) {
                    Enterprise e = enterpriseService.selectEnterpriseById(eId);
                    if (e != null) {
                        enterpriseName = e.getName();
                    }

                }

                item.put("id", p.getId());
                item.put("name", p.getName());
                item.put("enterpriseId", eId);
                item.put("enterpriseName", enterpriseName);
                item.put("standardName", p.getStandardName());
                item.put("productCategoryId", p.getProductCategoryId());
                item.put("standardNo", p.getStandardNo());
                item.put("specification", p.getSpecification());
                item.put("orginEnterpriseId", p.getOrginEnterpriseId());
                item.put("factoryEnterpriseId", p.getFactoryEnterpriseId());
                item.put("produceProcessId", p.getProduceProcessId());
                item.put("growthProcessId", p.getGrowthProcessId());
                item.put("certificationIds", p.getCertificationIds());
                item.put("video", p.getVideo());
                item.put("description", p.getDescription());
                item.put("standardDescription", p.getStandardDescription());
                item.put("edibleMethod", p.getEdibleMethod());
                item.put("photos", p.getPhotos());
                item.put("createTime", DateFormatUtil.format(p.getCreateTime()));
                item.put("updateTime", DateFormatUtil.format(p.getUpdateTime()));

                ProductLists.add(item);
            }

            data.put("items", ProductLists);

            return success(data);

        }else{
            return error("企业id不能为空");
        }
    }

    ///**
    // * 导出产品管理列表
    // */
    //@PreAuthorize("@ss.hasPermi('suyuan:product:export')")
    //@Log(title = "产品管理", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //public void export(HttpServletResponse response, Product product)
    //{
    //    List<Product> list = productService.selectProductList(product);
    //    ExcelUtil<Product> util = new ExcelUtil<Product>(Product.class);
    //    util.exportExcel(response, list, "产品管理数据");
    //}

    /**
     * 获取产品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('suyuan:product:query')")
    @ApiOperation("获取产品详情,原接口: /product/get")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        Map<String, Object> data = new HashMap<>();
        Product product = productService.selectProductById(id);
        if (product == null) {
            return error("产品不存在");
        }
        Integer enterpriseId = product.getEnterpriseId();
        String enterpriseName = "";
        if (enterpriseId != null) {
            Enterprise enterprise = enterpriseService.selectEnterpriseById(enterpriseId);
            if (enterprise != null) {
                enterpriseName = enterprise.getName();
            }
        }

        data.put("id", product.getId());
        data.put("name", product.getName());
        data.put("enterpriseId", enterpriseId);
        data.put("enterpriseName", enterpriseName);
        data.put("standardName", product.getStandardName());
        data.put("productCategoryId", product.getProductCategoryId());
        data.put("standardNo", product.getStandardNo());
        data.put("specification",product.getSpecification());
        data.put("orginEnterpriseId",product.getOrginEnterpriseId());
        data.put("factoryEnterpriseId",product.getFactoryEnterpriseId());
        data.put("produceProcessId",product.getProduceProcessId());
        data.put("growthProcessId",product.getGrowthProcessId());
        data.put("certificationIds", product.getCertificationIds());
        data.put("video", product.getVideo());
        data.put("description", product.getDescription());
        data.put("standardDescription", product.getStandardDescription());
        data.put("edibleMethod", product.getEdibleMethod());
        data.put("photos", product.getPhotos());
        data.put("createTime", DateFormatUtil.format(product.getCreateTime()));
        data.put("updateTime", DateFormatUtil.format(product.getUpdateTime()));

        return success(data);
    }

    /**
     * 新增产品管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:product:add')")
    @Log(title = "产品管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增产品,原接口: /product/create")
    @PostMapping
    public AjaxResult add(@RequestBody Product product)
    {
        // 产品名称不能重复
        Product product1 = new Product();
        product1.setName(product.getName());
        product1.setEnterpriseId(product.getEnterpriseId());
        Product product2 = productService.selectProduct(product1);
        if (product2 != null) {
            return error("产品已存在,请更改");
        }

        return toAjax(productService.insertProduct(product));
    }

    /**
     * 修改产品管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:product:edit')")
    @Log(title = "产品管理", businessType = BusinessType.UPDATE)
    @ApiOperation("修改产品,原接口: /product/update")
    @PutMapping
    public AjaxResult edit(@RequestBody Product product)
    {
        Integer id = product.getId();
        Product product1 = new Product();
        product1.setName(product.getName());
        product1.setEnterpriseId(product.getEnterpriseId());
        Product product2 = productService.selectProduct(product1);
        if (product2 != null && !product2.getId().equals(id)) {
            return error("产品已存在,请更改");
        }

        return toAjax(productService.updateProduct(product));
    }

    /**
     * 删除产品管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:product:remove')")
    @Log(title = "产品管理", businessType = BusinessType.DELETE)
    @ApiOperation("删除产品,多个id之间用逗号隔开。原接口: /product/delete")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(productService.deleteProductByIds(ids));
    }
}
