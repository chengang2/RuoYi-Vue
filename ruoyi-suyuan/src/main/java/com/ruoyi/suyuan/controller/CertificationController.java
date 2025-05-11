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

    //@ApiOperation("获取用户列表111")
    //@GetMapping("/list1")
    //public R<List<String>> userList()
    //{
    //    Map<Integer, String> users = new LinkedHashMap<Integer, String>();
    //    {
    //        users.put(1, "15888888888");
    //        users.put(2, "15666666666");
    //    }
    //    List<String> userList = new ArrayList<String>(users.values());
    //    return R.ok(userList);
    //}

    /**
     * 查询认证列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:certification:list')")
    @GetMapping("/list")
    public TableDataInfo list(Certification certification)
    {
        startPage();
        List<Certification> list = certificationService.selectCertificationList(certification);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('suyuan:certification:getAll')")
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
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(certificationService.deleteCertificationByIds(ids));
    }
}
