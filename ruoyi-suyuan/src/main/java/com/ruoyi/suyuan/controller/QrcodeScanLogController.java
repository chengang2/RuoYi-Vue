package com.ruoyi.suyuan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.suyuan.domain.ScanLogCName;
import com.ruoyi.suyuan.domain.ScanLogPName;
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
import com.ruoyi.suyuan.domain.QrcodeScanLog;
import com.ruoyi.suyuan.service.IQrcodeScanLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 二维码扫描日志管理Controller
 * 
 * @author chengang
 * @date 2025-05-15
 */
@Api(tags = "溯源模块-日志管理")
@RestController
@RequestMapping("/suyuan/qrcodescanlog")
public class QrcodeScanLogController extends BaseController
{
    @Autowired
    private IQrcodeScanLogService qrcodeScanLogService;

    /**
     * 查询二维码扫描日志管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:qrcodescanlog:list')")
    @GetMapping("/list")
    public TableDataInfo list(QrcodeScanLog qrcodeScanLog)
    {
        startPage();
        List<QrcodeScanLog> list = qrcodeScanLogService.selectQrcodeScanLogList(qrcodeScanLog);
        return getDataTable(list);
    }

    ///**
    // * 导出二维码扫描日志管理列表
    // */
    //@Log(title = "二维码扫描日志管理", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //public void export(HttpServletResponse response, QrcodeScanLog qrcodeScanLog)
    //{
    //    List<QrcodeScanLog> list = qrcodeScanLogService.selectQrcodeScanLogList(qrcodeScanLog);
    //    ExcelUtil<QrcodeScanLog> util = new ExcelUtil<QrcodeScanLog>(QrcodeScanLog.class);
    //    util.exportExcel(response, list, "二维码扫描日志管理数据");
    //}

    /**
     * 获取二维码扫描日志管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('suyuan:qrcodescanlog:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(qrcodeScanLogService.selectQrcodeScanLogById(id));
    }

    /**
     * 新增二维码扫描日志管理
     */
    @Log(title = "二维码扫描日志管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增扫描日志,原接口: /log/scan/create")
    @PostMapping
    public AjaxResult add(@RequestBody QrcodeScanLog qrcodeScanLog)
    {
        return toAjax(qrcodeScanLogService.insertQrcodeScanLog(qrcodeScanLog));
    }

    /**
     * 修改二维码扫描日志管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:qrcodescanlog:edit')")
    @Log(title = "二维码扫描日志管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QrcodeScanLog qrcodeScanLog)
    {
        return toAjax(qrcodeScanLogService.updateQrcodeScanLog(qrcodeScanLog));
    }

    /**
     * 删除二维码扫描日志管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:qrcodescanlog:remove')")
    @Log(title = "二维码扫描日志管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(qrcodeScanLogService.deleteQrcodeScanLogByIds(ids));
    }


    @ApiOperation("获取扫描日志,原接口: /log/scan/distribution")
    @GetMapping("/distribution")
    public AjaxResult distribution()
    {
        Map<String, Object> data = new java.util.HashMap<>();
        int total = qrcodeScanLogService.selectScanTotalCount();
        List<ScanLogCName> scanLogCNames = qrcodeScanLogService.selectScanCountByCity();
        List<Map<String, Object>> list = new ArrayList<>();
        for (ScanLogCName scanLogCName : scanLogCNames) {
            Map<String, Object> item = new java.util.HashMap<>();
            item.put("cname", scanLogCName.getCname());
            item.put("count", scanLogCName.getCount());
            list.add(item);
        }
        data.put("items", list);
        data.put("total", total);

        return success(data);
    }

    @ApiOperation("获取扫描日志,原接口: /log/scan/productCategory")
    @GetMapping("/productCategory")
    public AjaxResult productCategory()
    {
        Map<String, Object> data = new java.util.HashMap<>();

        List<ScanLogPName> scanLogPNames = qrcodeScanLogService.selectScanCountByProductCategory();
        List<Map<String, Object>> list = new ArrayList<>();
        for (ScanLogPName scanLogPName : scanLogPNames) {
            Map<String, Object> item = new java.util.HashMap<>();
            item.put("name", scanLogPName.getName());
            item.put("count", scanLogPName.getCount());
            list.add(item);
        }
        data.put("items", list);

        return success(data);
    }

}
