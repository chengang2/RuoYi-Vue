package com.ruoyi.suyuan.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.ruoyi.suyuan.domain.TSample;
import com.ruoyi.suyuan.service.ITSampleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 样品管理Controller
 * 
 * @author ruoyi
 * @date 2025-06-12
 */
@RestController
@RequestMapping("/suyuan/sample")
public class TSampleController extends BaseController
{
    @Autowired
    private ITSampleService tSampleService;

    /**
     * 查询样品管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:sample:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSample tSample)
    {
        startPage();
        List<TSample> list = tSampleService.selectTSampleList(tSample);
        return getDataTable(list);
    }

    /**
     * 导出样品管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:sample:export')")
    @Log(title = "样品管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TSample tSample)
    {
        List<TSample> list = tSampleService.selectTSampleList(tSample);
        ExcelUtil<TSample> util = new ExcelUtil<TSample>(TSample.class);
        util.exportExcel(response, list, "样品管理数据");
    }

    /**
     * 获取样品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('suyuan:sample:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tSampleService.selectTSampleById(id));
    }

    /**
     * 新增样品管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:sample:add')")
    @Log(title = "样品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody TSample tSample)
    {
        return toAjax(tSampleService.insertTSample(tSample));
    }

    /**
     * 修改样品管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:sample:edit')")
    @Log(title = "样品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSample tSample)
    {
        return toAjax(tSampleService.updateTSample(tSample));
    }

    /**
     * 删除样品管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:sample:remove')")
    @Log(title = "样品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tSampleService.deleteTSampleByIds(ids));
    }
}
