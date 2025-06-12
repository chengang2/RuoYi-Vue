package com.ruoyi.suyuan.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.suyuan.domain.TDetect;
import com.ruoyi.suyuan.service.ITDetectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 检测管理Controller
 * 
 * @author ruoyi
 * @date 2025-06-12
 */
@RestController
@RequestMapping("/suyuan/detect")
public class TDetectController extends BaseController
{
    @Autowired
    private ITDetectService tDetectService;

    /**
     * 查询检测管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:detect:list')")
    @GetMapping("/list")
    public TableDataInfo list(TDetect tDetect)
    {
        startPage();
        List<TDetect> list = tDetectService.selectTDetectList(tDetect);
        return getDataTable(list);
    }

    /**
     * 导出检测管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:detect:export')")
    @Log(title = "检测管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TDetect tDetect)
    {
        List<TDetect> list = tDetectService.selectTDetectList(tDetect);
        ExcelUtil<TDetect> util = new ExcelUtil<TDetect>(TDetect.class);
        util.exportExcel(response, list, "检测管理数据");
    }

    /**
     * 获取检测管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('suyuan:detect:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tDetectService.selectTDetectById(id));
    }

    /**
     * 新增检测管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:detect:add')")
    @Log(title = "检测管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TDetect tDetect)
    {
        return toAjax(tDetectService.insertTDetect(tDetect));
    }

    /**
     * 修改检测管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:detect:edit')")
    @Log(title = "检测管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TDetect tDetect)
    {
        return toAjax(tDetectService.updateTDetect(tDetect));
    }

    /**
     * 删除检测管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:detect:remove')")
    @Log(title = "检测管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tDetectService.deleteTDetectByIds(ids));
    }
}
