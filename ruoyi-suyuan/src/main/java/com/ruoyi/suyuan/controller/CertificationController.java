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
@RestController
@RequestMapping("/suyuan/certification")
public class CertificationController extends BaseController
{
    @Autowired
    private ICertificationService certificationService;

    @ApiOperation("获取用户列表111")
    @GetMapping("/list1")
    public R<List<String>> userList()
    {
        Map<Integer, String> users = new LinkedHashMap<Integer, String>();
        {
            users.put(1, "15888888888");
            users.put(2, "15666666666");
        }
        List<String> userList = new ArrayList<String>(users.values());
        return R.ok(userList);
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:certification:list')")
    @GetMapping("/list")
    public TableDataInfo list(Certification certification)
    {
        startPage();
        List<Certification> list = certificationService.selectCertificationList(certification);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:certification:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Certification certification)
    {
        List<Certification> list = certificationService.selectCertificationList(certification);
        ExcelUtil<Certification> util = new ExcelUtil<Certification>(Certification.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:certification:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(certificationService.selectCertificationById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:certification:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Certification certification)
    {
        return toAjax(certificationService.insertCertification(certification));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:certification:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Certification certification)
    {
        return toAjax(certificationService.updateCertification(certification));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:certification:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(certificationService.deleteCertificationByIds(ids));
    }
}
