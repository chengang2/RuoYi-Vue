package com.ruoyi.suyuan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.suyuan.domain.*;
import com.ruoyi.suyuan.service.*;
import com.ruoyi.suyuan.tools.DateFormatUtil;
import com.ruoyi.suyuan.tools.Md5Util;
import com.ruoyi.suyuan.tools.PdfUtil;
import com.ruoyi.suyuan.tools.QRCodeUtil;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品批次管理Controller
 * 
 * @author chengang
 * @date 2025-05-14
 */
@Api(tags = "溯源模块-产品批次管理")
@RestController
@RequestMapping("/suyuan/productbatch")
public class ProductBatchController extends BaseController
{
    @Autowired
    private IProductBatchService productBatchService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IEnterpriseService enterpriseService;
    @Autowired
    private IGrowthProcessService growthProcessService;
    @Autowired
    private IGrowthProcessStepService growthProcessStepService;
    @Autowired
    private IProduceProcessService produceProcessService;
    @Autowired
    private IProduceProcessStepService produceProcessStepService;
    @Autowired
    private ICertificationService certificationService;
    @Autowired
    private QRCodeUtil qrCodeUtil;
    /**
     * 查询产品批次管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:productbatch:list')")
    @ApiOperation("获取产品批次列表,原接口: /productBatch/getAllByPage")
    @GetMapping("/list")
    public TableDataInfo list(ProductBatchVO productBatchVO)
    {
        List<Integer> productIds = new ArrayList<>();

        Integer enterpriseId = productBatchVO.getEnterpriseId();

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
            for (Product product : products) {
                Integer id = product.getId();
                productIds.add(id);
            }
        }
        if(!productIds.isEmpty()) {
            ProductBatchList productBatchList = new ProductBatchList();
            productBatchList.setProductIds(productIds);

            startPage();
            List<ProductBatch> list = productBatchService.selectProductBatchList(productBatchList);

            PageInfo<ProductBatch> pageInfo = new PageInfo<>(list);

            List<Map<String, Object>> items = new ArrayList<>();

            for (ProductBatch productBatch : list) {
                Map<String, Object> item = new java.util.HashMap<>();
                Integer productId = productBatch.getProductId();
                String productName = "";
                String enterpriseName = "";
                String specification = "";
                if (productId != null) {
                    Product product = productService.selectProductById(productId);
                    if (product != null) {
                        productName = product.getName();
                        specification = product.getSpecification();
                    }
                    Integer eId = product.getEnterpriseId();
                    if (eId != null) {
                        Enterprise enterprise = enterpriseService.selectEnterpriseById(eId);
                        if (enterprise != null) {
                            enterpriseName = enterprise.getName();
                        }
                    }
                }
                item.put("id", productBatch.getId());
                item.put("productId", productId);
                item.put("specification", specification);
                item.put("enterpriseName", enterpriseName);
                item.put("productName", productName);
                item.put("batchNumber", productBatch.getBatchNumber());
                item.put("produceDatetime", DateFormatUtil.format(productBatch.getProduceDatetime()));
                item.put("testReport", productBatch.getTestReport());
                item.put("testReportPicture", productBatch.getTestReportPicture());
                item.put("qrCode", productBatch.getQrCode());
                item.put("printNum", productBatch.getPrintNum());
                item.put("md5Code", productBatch.getMd5Code());
                item.put("status", productBatch.getStatus());
                item.put("createTime", DateFormatUtil.format(productBatch.getCreateTime()));
                item.put("updateTime", DateFormatUtil.format(productBatch.getUpdateTime()));

                items.add(item);
            }
            // 用原始list的分页信息，返回你组装的items
            TableDataInfo rspData = new TableDataInfo();
            rspData.setCode(HttpStatus.SUCCESS);
            rspData.setMsg("查询成功");
            rspData.setRows(items);
            rspData.setTotal(pageInfo.getTotal());
            return rspData;
        }else{
            return getDataTable(new ArrayList<>());
        }

    }

    @PreAuthorize("@ss.hasPermi('suyuan:productbatch:list')")
    @ApiOperation("获取产品批次列表,原接口: /productBatch/getAll")
    @GetMapping("/getAll")
    public AjaxResult getAll(ProductBatchVO productBatchVO)
    {
        Map<String, Object> data = new java.util.HashMap<>();

        List<Integer> productIds = new ArrayList<>();

        Integer enterpriseId = productBatchVO.getEnterpriseId();

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
            for (Product product : products) {
                Integer id = product.getId();
                productIds.add(id);
            }
        }
        if(!productIds.isEmpty()) {
            ProductBatchList productBatchList = new ProductBatchList();
            productBatchList.setProductIds(productIds);

            List<ProductBatch> list = productBatchService.selectProductBatchList(productBatchList);
            List<Map<String, Object>> items = new ArrayList<>();
            for (ProductBatch productBatch : list) {
                Map<String, Object> item = new java.util.HashMap<>();
                Integer productId = productBatch.getProductId();
                String productName = "";
                String enterpriseName = "";
                String specification = "";
                if (productId != null) {
                    Product product = productService.selectProductById(productId);
                    if (product != null) {
                        productName = product.getName();
                        specification = product.getSpecification();
                    }
                    Integer eId = product.getEnterpriseId();
                    if (eId != null) {
                        Enterprise enterprise = enterpriseService.selectEnterpriseById(eId);
                        if (enterprise != null) {
                            enterpriseName = enterprise.getName();
                        }
                    }
                }
                item.put("id", productBatch.getId());
                item.put("productId", productId);
                item.put("specification", specification);
                item.put("enterpriseName", enterpriseName);
                item.put("productName", productName);
                item.put("batchNumber", productBatch.getBatchNumber());
                item.put("produceDatetime", DateFormatUtil.format(productBatch.getProduceDatetime()));
                item.put("testReport", productBatch.getTestReport());
                item.put("testReportPicture", productBatch.getTestReportPicture());
                item.put("qrCode", productBatch.getQrCode());
                item.put("printNum", productBatch.getPrintNum());
                item.put("md5Code", productBatch.getMd5Code());
                item.put("status", productBatch.getStatus());
                item.put("createTime", DateFormatUtil.format(productBatch.getCreateTime()));
                item.put("updateTime", DateFormatUtil.format(productBatch.getUpdateTime()));

                items.add(item);
            }

            data.put("items",items);
        }
        return success(data);
    }

    ///**
    // * 导出产品批次管理列表
    // */
    //@PreAuthorize("@ss.hasPermi('suyuan:productbatch:export')")
    //@Log(title = "产品批次管理", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //public void export(HttpServletResponse response, ProductBatch productBatch)
    //{
    //    List<ProductBatch> list = productBatchService.selectProductBatchList(productBatch);
    //    ExcelUtil<ProductBatch> util = new ExcelUtil<ProductBatch>(ProductBatch.class);
    //    util.exportExcel(response, list, "产品批次管理数据");
    //}

    /**
     * 获取产品批次管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('suyuan:productbatch:query')")
    @ApiOperation("获取产品批次详情,原接口: /productBatch/get")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        Map<String,Object> data = new java.util.HashMap<>();
        ProductBatch productBatch = productBatchService.selectProductBatchById(id);
        if (productBatch == null) {
            return error("产品批次不存在");
        }
        Integer productId = productBatch.getProductId();
        if (productId == null) {
            return error("产品id不能为空");
        }
        Product product = productService.selectProductById(productId);
        if (product == null) {
            return error("产品不存在");
        }
        String enterpriseName = "";
        Integer enterpriseId = product.getEnterpriseId();
        if (enterpriseId != null) {
            Enterprise enterprise = enterpriseService.selectEnterpriseById(enterpriseId);
            if (enterprise != null) {
                enterpriseName = enterprise.getName();
            }
        }
        data.put("id", productBatch.getId());
        data.put("enterpriseName", enterpriseName);
        data.put("productId", productId);
        data.put("productName", product.getName());
        data.put("specification", product.getSpecification());
        data.put("batchNumber", productBatch.getBatchNumber());
        data.put("produceDatetime", DateFormatUtil.format(productBatch.getProduceDatetime()));
        data.put("testReport", productBatch.getTestReport());
        data.put("testReportPicture", productBatch.getTestReportPicture());
        data.put("qrCode", productBatch.getQrCode());
        data.put("printNum", productBatch.getPrintNum());
        data.put("md5Code", productBatch.getMd5Code());
        data.put("status", productBatch.getStatus());
        data.put("createTime", DateFormatUtil.format(productBatch.getCreateTime()));
        data.put("updateTime", DateFormatUtil.format(productBatch.getUpdateTime()));

        return success(data);
    }

    /**
     * 获取产品批次管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('suyuan:productbatch:query')")
    @ApiOperation("获取产品批次url,原接口: /productBatch/getUrl")
    @GetMapping(value = "/getUrl/{id}")
    public AjaxResult getUrl(@PathVariable("id") Integer id){
        Map<String,Object> data = new java.util.HashMap<>();
        ProductBatch productBatch = productBatchService.selectProductBatchById(id);
        if (productBatch == null) {
            return error("产品批次不存在");
        }
        String md5Code = productBatch.getMd5Code();
        if(md5Code.isEmpty()){
            md5Code = String.valueOf(id);
        }
        String urlPath = qrCodeUtil.getUrlPath();

        data.put("url",urlPath + md5Code);

        return success(data);
    }

    /**
     * 新增产品批次管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:productbatch:add')")
    @Log(title = "产品批次管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增产品批次,原接口: /productBatch/create")
    @PostMapping
    public AjaxResult add(@RequestBody ProductBatch productBatch)
    {
        ProductBatch productBatch1 = new ProductBatch();
        productBatch1.setBatchNumber(productBatch.getBatchNumber());
        ProductBatch productBatch2 = productBatchService.selectProductBatch(productBatch1);
        if (productBatch2 != null) {
            return error("产品批次已存在,请更改");
        }
        String testReport = productBatch.getTestReport();
        if (testReport != null && !testReport.isEmpty()) {
            try {
                String testReportPicture = PdfUtil.pdf2Jpg(testReport);
                productBatch.setTestReportPicture(testReportPicture);
            } catch (Exception e) {
                throw new RuntimeException("pdf转jpg失败:"+e);
            }
        }
        int i = productBatchService.insertProductBatch(productBatch);
        if (i > 0) {
            Integer productId = productBatch.getProductId();
            if (productId != null) {
                Product product = productService.selectProductById(productId);
                if (product != null) {
                    String productName = product.getName();
                    Integer enterpriseId = product.getEnterpriseId();
                    if (enterpriseId != null) {
                        Enterprise enterprise = enterpriseService.selectEnterpriseById(enterpriseId);
                        String enterpriseName = enterprise.getName();
                        String md5Code = Md5Util.encodeMD5(String.valueOf(productId));
                        try {
                            String qrCode = qrCodeUtil.getQRCode(String.valueOf(productId), md5Code, productName, enterpriseName, 256, 256);
                            Integer id = productBatch.getId();
                            System.out.println("id = " + id);
                            ProductBatch p = new ProductBatch();
                            p.setId(id);
                            p.setQrCode(qrCode);
                            p.setMd5Code(md5Code);
                            productBatchService.updateProductBatch(p);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

            return success("新增成功");
        } else {
            return error("新增失败");
        }

    }

    /**
     * 修改产品批次管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:productbatch:edit')")
    @Log(title = "产品批次管理", businessType = BusinessType.UPDATE)
    @ApiOperation("修改产品批次,原接口: /productBatch/update,/productBatch/update_status,/productBatch/updatePrintNum")
    @PutMapping
    public AjaxResult edit(@RequestBody ProductBatch productBatch)
    {
        String testReport = productBatch.getTestReport();
        if (testReport != null && !testReport.isEmpty()) {
            try {
                String testReportPicture = PdfUtil.pdf2Jpg(testReport);
                productBatch.setTestReportPicture(testReportPicture);
            } catch (Exception e) {
                throw new RuntimeException("pdf转jpg失败:" + e);
            }
        }
        return toAjax(productBatchService.updateProductBatch(productBatch));
    }

    /**
     * 删除产品批次管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:productbatch:remove')")
    @Log(title = "产品批次管理", businessType = BusinessType.DELETE)
    @ApiOperation("删除产品批次,多个id之间用逗号隔开。原接口: /productBatch/delete")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(productBatchService.deleteProductBatchByIds(ids));
    }

    @ApiOperation("获取企业详情,原接口: /productBatch/detail")
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id)
    {
        Map<String,Object> data = new java.util.HashMap<>();
        ProductBatch productBatch = productBatchService.selectProductBatchById(id);
        if (productBatch == null) {
            return error("产品批次不存在");
        }

        Integer status = productBatch.getStatus();
        if(status != null && status == 1){
            return error("二维码已失效，请联系管理员");
        }

        Integer productId = productBatch.getProductId();
        if (productId == null) {
            return error("产品id不能为空");
        }
        Map<String,Object> dataProduct = new HashMap<>();

        Product product = productService.selectProductById(productId);
        if (product == null) {
            return error("产品不存在");
        }
        Map<String,Object> dataOriginEnterprise = new HashMap<>();
        Integer orginEnterpriseId = product.getOrginEnterpriseId();
        String originEnterpriseName = "";
        if (orginEnterpriseId != null) {
            Enterprise originEnterprise = enterpriseService.selectEnterpriseById(orginEnterpriseId);
            if (originEnterprise != null) {
                originEnterpriseName = originEnterprise.getName();
            }
        }
        dataOriginEnterprise.put("id", orginEnterpriseId);
        dataOriginEnterprise.put("name", originEnterpriseName);

        Map<String,Object> dataFactoryEnterprise = new HashMap<>();
        Integer factoryEnterpriseId = product.getFactoryEnterpriseId();
        String factoryEnterpriseName = "";
        if(factoryEnterpriseId != null){
            Enterprise factoryEnterprise = enterpriseService.selectEnterpriseById(factoryEnterpriseId);
            if (factoryEnterprise != null) {
                factoryEnterpriseName = factoryEnterprise.getName();
            }
        }
        dataFactoryEnterprise.put("id", factoryEnterpriseId);
        dataFactoryEnterprise.put("name", factoryEnterpriseName);

        Map<String,Object> dataGrowthProcess = new HashMap<>();
        Integer growthProcessId = product.getGrowthProcessId();
        String growthProcessName = "";
        if(growthProcessId != null){
            GrowthProcess growthProcess = growthProcessService.selectGrowthProcessById(growthProcessId);
            if (growthProcess != null) {
                growthProcessName = growthProcess.getName();
            }
        }
        dataGrowthProcess.put("name", growthProcessName);
        GrowthProcessStep growthProcessStep = new GrowthProcessStep();
        growthProcessStep.setGrowthProcessId(growthProcessId);
        List<GrowthProcessStep> growthProcessSteps = growthProcessStepService.selectGrowthProcessStepList(growthProcessStep);
        List<Map<String,Object>> dataGrowthProcessSteps = new ArrayList<>();
        for (GrowthProcessStep growthProcessStep1 : growthProcessSteps) {
            Map<String,Object> item = new HashMap<>();
            item.put("name", growthProcessStep1.getName());
            item.put("picture", growthProcessStep1.getPicture());

            dataGrowthProcessSteps.add(item);
        }
        dataGrowthProcess.put("steps", dataGrowthProcessSteps);

        Map<String,Object> dataProduceProcess = new HashMap<>();
        Integer produceProcessId = product.getProduceProcessId();
        String produceProcessName = "";
        ProduceProcess produceProcess = produceProcessService.selectProduceProcessById(produceProcessId);
        if (produceProcess != null) {
            produceProcessName = produceProcess.getName();
        }
        dataProduceProcess.put("name", produceProcessName);

        ProduceProcessStep produceProcessStep = new ProduceProcessStep();
        produceProcessStep.setProduceProcessId(produceProcessId);
        List<ProduceProcessStep> produceProcessSteps = produceProcessStepService.selectProduceProcessStepList(produceProcessStep);
        List<Map<String,Object>> dataProduceProcessSteps = new ArrayList<>();
        for (ProduceProcessStep produceProcessStep1 : produceProcessSteps) {
            Map<String,Object> item = new HashMap<>();
            item.put("name", produceProcessStep1.getName());
            item.put("picture", produceProcessStep1.getPicture());

            dataProduceProcessSteps.add(item);
        }
        dataProduceProcess.put("steps", dataProduceProcessSteps);

        List<Map<String,Object>> dataCertifications = new ArrayList<>();
        String certificationIds = product.getCertificationIds();
        String[] certificationIdArr = certificationIds.split(",");
        for (String certificationId : certificationIdArr) {
            if(!certificationId.isEmpty()){
                Map<String,Object> item = new HashMap<>();
                Integer cid = Integer.valueOf(certificationId);
                Certification certification = certificationService.selectCertificationById(cid);
                if (certification != null) {
                    item.put("id", certification.getId());
                    item.put("name", certification.getName());
                    item.put("logo", certification.getLogo());
                    item.put("license", certification.getLicense());

                    dataCertifications.add(item);
                }
            }
        }
        dataProduct.put("id",product.getId());
        dataProduct.put("name",product.getName());
        dataProduct.put("standardDescription",product.getStandardDescription());
        dataProduct.put("standardName",product.getStandardName());
        dataProduct.put("description",product.getDescription());
        dataProduct.put("video",product.getVideo());
        dataProduct.put("standardNo",product.getStandardNo());
        dataProduct.put("edibleMethod",product.getEdibleMethod());
        dataProduct.put("specification",product.getSpecification());
        dataProduct.put("productCategoryId",product.getProductCategoryId());
        dataProduct.put("photos",product.getPhotos());
        dataProduct.put("originEnterpriseId",orginEnterpriseId);
        dataProduct.put("originEnterprise",dataOriginEnterprise);
        dataProduct.put("factoryEnterpriseId",factoryEnterpriseId);
        dataProduct.put("factoryEnterprise",dataFactoryEnterprise);
        dataProduct.put("growthProcessId",growthProcessId);
        dataProduct.put("growthProcess",dataGrowthProcess);
        dataProduct.put("produceProcessId",produceProcessId);
        dataProduct.put("produceProcess",dataProduceProcess);

        dataProduct.put("enterpriseId",product.getEnterpriseId());
        dataProduct.put("certificationIds",certificationIds);
        dataProduct.put("certifications",dataCertifications);

        data.put("id", productBatch.getId());
        data.put("produceDatetime",DateFormatUtil.format(productBatch.getProduceDatetime()));
        data.put("productName", product.getName());
        data.put("testReport", productBatch.getTestReport());
        data.put("testReportPicture", productBatch.getTestReportPicture());
        data.put("product",dataProduct);

        return success(data);
    }

    @ApiOperation("获取企业详情,原接口: /productBatch/detail2")
    @GetMapping(value = "/detail2/{id}")
    public AjaxResult detail2(@PathVariable("id") String id){
        Map<String,Object> data = new java.util.HashMap<>();
        ProductBatch pb = new ProductBatch();
        pb.setMd5Code(id);
        ProductBatch productBatch = productBatchService.selectProductBatch(pb);
        if (productBatch == null) {
            return error("产品批次不存在");
        }

        Integer status = productBatch.getStatus();
        if(status != null && status == 1){
            return error("二维码已失效，请联系管理员");
        }

        Integer productId = productBatch.getProductId();
        if (productId == null) {
            return error("产品id不能为空");
        }
        Map<String,Object> dataProduct = new HashMap<>();

        Product product = productService.selectProductById(productId);
        if (product == null) {
            return error("产品不存在");
        }
        Map<String,Object> dataOriginEnterprise = new HashMap<>();
        Integer orginEnterpriseId = product.getOrginEnterpriseId();
        String originEnterpriseName = "";
        if (orginEnterpriseId != null) {
            Enterprise originEnterprise = enterpriseService.selectEnterpriseById(orginEnterpriseId);
            if (originEnterprise != null) {
                originEnterpriseName = originEnterprise.getName();
            }
        }
        dataOriginEnterprise.put("id", orginEnterpriseId);
        dataOriginEnterprise.put("name", originEnterpriseName);

        Map<String,Object> dataFactoryEnterprise = new HashMap<>();
        Integer factoryEnterpriseId = product.getFactoryEnterpriseId();
        String factoryEnterpriseName = "";
        if(factoryEnterpriseId != null){
            Enterprise factoryEnterprise = enterpriseService.selectEnterpriseById(factoryEnterpriseId);
            if (factoryEnterprise != null) {
                factoryEnterpriseName = factoryEnterprise.getName();
            }
        }
        dataFactoryEnterprise.put("id", factoryEnterpriseId);
        dataFactoryEnterprise.put("name", factoryEnterpriseName);

        Map<String,Object> dataGrowthProcess = new HashMap<>();
        Integer growthProcessId = product.getGrowthProcessId();
        String growthProcessName = "";
        if(growthProcessId != null){
            GrowthProcess growthProcess = growthProcessService.selectGrowthProcessById(growthProcessId);
            if (growthProcess != null) {
                growthProcessName = growthProcess.getName();
            }
        }
        dataGrowthProcess.put("name", growthProcessName);
        GrowthProcessStep growthProcessStep = new GrowthProcessStep();
        growthProcessStep.setGrowthProcessId(growthProcessId);
        List<GrowthProcessStep> growthProcessSteps = growthProcessStepService.selectGrowthProcessStepList(growthProcessStep);
        List<Map<String,Object>> dataGrowthProcessSteps = new ArrayList<>();
        for (GrowthProcessStep growthProcessStep1 : growthProcessSteps) {
            Map<String,Object> item = new HashMap<>();
            item.put("name", growthProcessStep1.getName());
            item.put("picture", growthProcessStep1.getPicture());

            dataGrowthProcessSteps.add(item);
        }
        dataGrowthProcess.put("steps", dataGrowthProcessSteps);

        Map<String,Object> dataProduceProcess = new HashMap<>();
        Integer produceProcessId = product.getProduceProcessId();
        String produceProcessName = "";
        ProduceProcess produceProcess = produceProcessService.selectProduceProcessById(produceProcessId);
        if (produceProcess != null) {
            produceProcessName = produceProcess.getName();
        }
        dataProduceProcess.put("name", produceProcessName);

        ProduceProcessStep produceProcessStep = new ProduceProcessStep();
        produceProcessStep.setProduceProcessId(produceProcessId);
        List<ProduceProcessStep> produceProcessSteps = produceProcessStepService.selectProduceProcessStepList(produceProcessStep);
        List<Map<String,Object>> dataProduceProcessSteps = new ArrayList<>();
        for (ProduceProcessStep produceProcessStep1 : produceProcessSteps) {
            Map<String,Object> item = new HashMap<>();
            item.put("name", produceProcessStep1.getName());
            item.put("picture", produceProcessStep1.getPicture());

            dataProduceProcessSteps.add(item);
        }
        dataProduceProcess.put("steps", dataProduceProcessSteps);

        List<Map<String,Object>> dataCertifications = new ArrayList<>();
        String certificationIds = product.getCertificationIds();
        String[] certificationIdArr = certificationIds.split(",");
        for (String certificationId : certificationIdArr) {
            if(!certificationId.isEmpty()){
                Map<String,Object> item = new HashMap<>();
                Integer cid = Integer.valueOf(certificationId);
                Certification certification = certificationService.selectCertificationById(cid);
                if (certification != null) {
                    item.put("id", certification.getId());
                    item.put("name", certification.getName());
                    item.put("logo", certification.getLogo());
                    item.put("license", certification.getLicense());

                    dataCertifications.add(item);
                }
            }
        }
        dataProduct.put("id",product.getId());
        dataProduct.put("name",product.getName());
        dataProduct.put("standardDescription",product.getStandardDescription());
        dataProduct.put("standardName",product.getStandardName());
        dataProduct.put("description",product.getDescription());
        dataProduct.put("video",product.getVideo());
        dataProduct.put("standardNo",product.getStandardNo());
        dataProduct.put("edibleMethod",product.getEdibleMethod());
        dataProduct.put("specification",product.getSpecification());
        dataProduct.put("productCategoryId",product.getProductCategoryId());
        dataProduct.put("photos",product.getPhotos());
        dataProduct.put("originEnterpriseId",orginEnterpriseId);
        dataProduct.put("originEnterprise",dataOriginEnterprise);
        dataProduct.put("factoryEnterpriseId",factoryEnterpriseId);
        dataProduct.put("factoryEnterprise",dataFactoryEnterprise);
        dataProduct.put("growthProcessId",growthProcessId);
        dataProduct.put("growthProcess",dataGrowthProcess);
        dataProduct.put("produceProcessId",produceProcessId);
        dataProduct.put("produceProcess",dataProduceProcess);

        dataProduct.put("enterpriseId",product.getEnterpriseId());
        dataProduct.put("certificationIds",certificationIds);
        dataProduct.put("certifications",dataCertifications);

        data.put("id", productBatch.getId());
        data.put("produceDatetime",DateFormatUtil.format(productBatch.getProduceDatetime()));
        data.put("productName", product.getName());
        data.put("testReport", productBatch.getTestReport());
        data.put("testReportPicture", productBatch.getTestReportPicture());
        data.put("product",dataProduct);

        return success(data);
    }

}
