package com.ruoyi.suyuan.controller;

import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.suyuan.domain.*;
import com.ruoyi.suyuan.service.IEnterpriseService;
import com.ruoyi.suyuan.service.IProductBatchService;
import com.ruoyi.suyuan.service.IProductService;
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
import com.ruoyi.suyuan.service.IOperateLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 操作日志管理Controller
 * 
 * @author chengang
 * @date 2025-05-15
 */
@Api(tags = "溯源模块-日志管理")
@RestController
@RequestMapping("/suyuan/operatelog")
public class OperateLogController extends BaseController
{
    @Autowired
    private IOperateLogService operateLogService;
    @Autowired
    private IProductBatchService productBatchService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IEnterpriseService enterpriseService;

    /**
     * 查询操作日志管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:operatelog:list')")
    @ApiOperation("获取评论日志列表,原接口: /log/operate/get_all_by_page")
    @GetMapping("/list")
    public TableDataInfo list(OperateLogReq operateLogVO)
    {
        String batchNumber = operateLogVO.getBatchNumber();
        ProductBatchList productBatchList = new ProductBatchList();
        productBatchList.setBatchNumber(batchNumber);
        List<Integer> productIds = new ArrayList<>();
        List<ProductBatch> productBatches = productBatchService.selectProductBatchList(productBatchList);
        for (ProductBatch productBatch : productBatches) {
            productIds.add(productBatch.getProductId());
        }
        OperateLogList operateLogList = new OperateLogList();
        operateLogList.setProductIds(productIds);
        startPage();
        List<OperateLog> list = operateLogService.selectOperateLogList(operateLogList);
        PageInfo<OperateLog> pageInfo = new PageInfo<>(list);

        List<Map<String,Object>> items = new ArrayList<>();

        for (OperateLog operateLog : list) {
            Map<String,Object> item = new HashMap<>();
            Integer productId = operateLog.getProductId();
            String productName = "";
            Integer enterpriseId = 0;
            String specification = "";
            if (productId != null) {
                Product product = productService.selectProductById(productId);
                if (product != null) {
                    productName = product.getName();
                    enterpriseId = product.getEnterpriseId();
                    specification = product.getSpecification();
                }
            }
            String enterpriseName = "";
            if(enterpriseId != 0){
                Enterprise enterprise = enterpriseService.selectEnterpriseById(enterpriseId);
                if (enterprise != null) {
                    enterpriseName = enterprise.getName();
                }
            }
            ProductBatch pb = new ProductBatch();
            pb.setProductId(productId);
            ProductBatch p = productBatchService.selectProductBatch(pb);

            item.put("id",operateLog.getId());
            item.put("productId", productId);
            item.put("cname", operateLog.getCname());
            item.put("submitScore", operateLog.getSubmitScore());
            item.put("submitComment", operateLog.getSubmitComment());
            item.put("createTime", DateFormatUtil.format(operateLog.getCreateTime()));
            item.put("produceDatetime", DateFormatUtil.format(p.getProduceDatetime()));
            item.put("batchNumber", p.getBatchNumber());
            item.put("productName", productName);
            item.put("specification", specification);
            item.put("enterpriseName",enterpriseName);

            items.add(item);
        }

        // 用原始list的分页信息，返回你组装的items
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(items);
        rspData.setTotal(pageInfo.getTotal());
        return rspData;
    }

    ///**
    // * 导出操作日志管理列表
    // */
    //@PreAuthorize("@ss.hasPermi('suyuan:operatelog:export')")
    //@Log(title = "操作日志管理", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //public void export(HttpServletResponse response, OperateLog operateLog)
    //{
    //    List<OperateLog> list = operateLogService.selectOperateLogList(operateLog);
    //    ExcelUtil<OperateLog> util = new ExcelUtil<OperateLog>(OperateLog.class);
    //    util.exportExcel(response, list, "操作日志管理数据");
    //}

    /**
     * 获取操作日志管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('suyuan:operatelog:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(operateLogService.selectOperateLogById(id));
    }

    /**
     * 新增操作日志管理
     */
    @Log(title = "操作日志管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增评论日志,原接口: /log/operate/create")
    @PostMapping
    public AjaxResult add(@RequestBody OperateLog operateLog)
    {
        return toAjax(operateLogService.insertOperateLog(operateLog));
    }

    /**
     * 修改操作日志管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:operatelog:edit')")
    @Log(title = "操作日志管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OperateLog operateLog)
    {
        return toAjax(operateLogService.updateOperateLog(operateLog));
    }

    /**
     * 删除操作日志管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:operatelog:remove')")
    @Log(title = "操作日志管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(operateLogService.deleteOperateLogByIds(ids));
    }


    @ApiOperation("好评率按月分布图,原接口: /log/operate/monthDistribution")
    @GetMapping(value = "/monthDistribution")
    public AjaxResult monthDistribution()
    {
        Map<String, Object> data = new HashMap<>();

        List<OperateLogScoreMonth> operateLogScoreMonths = operateLogService.selectScoreByMonth();

        data.put("items",operateLogScoreMonths);
        return success(data);
    }

    @ApiOperation("好评率按产品分布图,原接口: /log/operate/productDistribution")
    @GetMapping(value = "/productDistribution")
    public AjaxResult productDistribution()
    {
        Map<String, Object> data = new HashMap<>();

        List<OperateLogScoreProduct> operateLogScoreProducts = operateLogService.selectScoreByProduct();

        data.put("items",operateLogScoreProducts);
        return success(data);
    }

    @ApiOperation("好评率按产品月份分布图,原接口: /log/operate/productMonthDistribution")
    @GetMapping(value = "/productMonthDistribution")
    public AjaxResult productMonthDistribution()
    {
        Map<String, Object> data = new HashMap<>();

        List<OperateLogScoreProductMonth> operateLogScoreProductMonths = operateLogService.selectScoreByProductAndMonth();
        // 1. 收集所有出现过的月份（去重）
        Set<Integer> allMonths = new TreeSet<>();
        for (OperateLogScoreProductMonth spm : operateLogScoreProductMonths) {
            allMonths.add(spm.getMonth());
        }
        // 2. 按产品名分组
        Map<String, List<OperateLogScoreProductMonth>> productMap = new LinkedHashMap<>();
        for (OperateLogScoreProductMonth spm : operateLogScoreProductMonths) {
            productMap.computeIfAbsent(spm.getName(), k -> new ArrayList<>()).add(spm);
        }
        // 3. 组装返回结构
        List<Map<String, Object>> items = new ArrayList<>();
        for (Map.Entry<String, List<OperateLogScoreProductMonth>> entry : productMap.entrySet()) {
            String productName = entry.getKey();
            List<OperateLogScoreProductMonth> monthList = entry.getValue();

            // 3.1 先把有数据的月份放进去
            Map<Integer, Integer> monthRateMap = new HashMap<>();
            for (OperateLogScoreProductMonth spm : monthList) {
                monthRateMap.put(spm.getMonth(), spm.getRate());
            }

            // 3.2 补全所有月份（没有的补0）
            List<Map<String, Object>> monthItems = new ArrayList<>();
            for (Integer month : allMonths) {
                Map<String, Object> monthItem = new HashMap<>();
                monthItem.put("month", month);
                monthItem.put("rate", monthRateMap.getOrDefault(month, 0));
                monthItems.add(monthItem);
            }

            Map<String, Object> productItem = new HashMap<>();
            productItem.put("name", productName);
            productItem.put("items", monthItems);
            items.add(productItem);
        }

        data.put("items", items);

        return success(data);
    }
}
