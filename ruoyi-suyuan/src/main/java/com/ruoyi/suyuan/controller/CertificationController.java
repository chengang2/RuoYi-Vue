package com.ruoyi.suyuan.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.suyuan.domain.Certification;
import com.ruoyi.suyuan.service.ICertificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
@Api(tags = "溯源模块-认证管理")
@RestController
@RequestMapping("/suyuan/certification")
public class CertificationController extends BaseController
{
    @Autowired
    private ICertificationService certificationService;

    /**
     * 查询认证列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:certification:list')")
    @ApiOperation("获取认证列表,原接口: /certification/getAllByPage")
    @GetMapping("/list")
    public TableDataInfo list(Certification certification)
    {
        startPage();
        List<Certification> list = certificationService.selectCertificationList(certification);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('suyuan:certification:getAll')")
    @ApiOperation("获取认证列表,原接口: /certification/getAll")
    @GetMapping("/getAll")
    public AjaxResult getAll(Certification certification)
    {
        List<Certification> list = certificationService.selectCertificationList(certification);
        return success(list);
    }

    ///**
    // * 导出认证列表
    // */
    //@PreAuthorize("@ss.hasPermi('suyuan:certification:export')")
    //@Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //public void export(HttpServletResponse response, Certification certification)
    //{
    //    List<Certification> list = certificationService.selectCertificationList(certification);
    //    ExcelUtil<Certification> util = new ExcelUtil<Certification>(Certification.class);
    //    util.exportExcel(response, list, "认证数据");
    //}

    /**
     * 获取认证详细信息
     */
    @PreAuthorize("@ss.hasPermi('suyuan:certification:query')")
    @ApiOperation("获取认证详情,原接口: /certification/get")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        if (id == null) {
            return error("id 不能为空");
        }
        return success(certificationService.selectCertificationById(id));
    }

    /**
     * 新增认证
     */
    @PreAuthorize("@ss.hasPermi('suyuan:certification:add')")
    @Log(title = "认证管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增认证,原接口: /certification/create")
    @PostMapping
    public AjaxResult add(@RequestBody Certification certification)
    {
        Certification certification1 = new Certification();
        certification1.setName(certification.getName());
        certification1.setEnterpriseId(certification.getEnterpriseId());
        Certification certification2 = certificationService.selectCertification(certification1);
        if (certification2 != null && certification2.getId() > 0) {
            return error("该企业已存在认证信息");
        }
        //certification.setCreateBy(getUsername());
        return toAjax(certificationService.insertCertification(certification));
    }

    /**
     * 修改认证
     */
    @PreAuthorize("@ss.hasPermi('suyuan:certification:edit')")
    @Log(title = "认证管理", businessType = BusinessType.UPDATE)
    @ApiOperation("修改认证,原接口: /certification/update")
    @PutMapping
    public AjaxResult edit(@RequestBody Certification certification)
    {
        return toAjax(certificationService.updateCertification(certification));
    }

    /**
     * 删除认证
     */
    @PreAuthorize("@ss.hasPermi('suyuan:certification:remove')")
    @Log(title = "认证管理", businessType = BusinessType.DELETE)
    @ApiOperation("删除认证,多个id之间用逗号隔开。原接口: /certification/delete")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(certificationService.deleteCertificationByIds(ids));
    }
}
