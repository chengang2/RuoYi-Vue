package com.ruoyi.suyuan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.suyuan.domain.DeviceInfo;
import com.ruoyi.suyuan.service.IDeviceInfoService;
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
import com.ruoyi.suyuan.domain.Enterprise;
import com.ruoyi.suyuan.service.IEnterpriseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 企业管理Controller
 * 
 * @author ruoyi
 * @date 2025-05-10
 */
@RestController
@RequestMapping("/suyuan/enterprise")
public class EnterpriseController extends BaseController
{
    @Autowired
    private IEnterpriseService enterpriseService;
    @Autowired
    private IDeviceInfoService deviceInfoService;

    /**
     * 查询企业管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:enterprise:list')")
    @GetMapping("/list")
    public TableDataInfo list(Enterprise enterprise)
    {
        startPage();
        List<Enterprise> list = enterpriseService.selectEnterpriseList(enterprise);
        return getDataTable(list);
    }

    /**
     * 获取企业管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:enterprise:list')")
    @GetMapping(value = "/getAll/{parentId}")
    public AjaxResult getChildrenInfo(@PathVariable("parentId") Integer parentId)
    {
        return success(enterpriseService.selectEnterpriseByParentId(parentId));
    }
    /**
     * 获取企业管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:enterprise:list')")
    @GetMapping(value = "/all/{parentId}")
    public AjaxResult getAll(@PathVariable("parentId") Integer parentId)
    {
        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> items = new ArrayList<>();
        List<Enterprise> enterprises = enterpriseService.selectEnterpriseByParentId(parentId);
        for (Enterprise enterprise: enterprises){
            Map<String, Object> item = new HashMap<>();

            Integer eId = enterprise.getId();
            DeviceInfo deviceInfo = deviceInfoService.selectDeviceInfoByEnterpriseId(eId);
            if(deviceInfo != null && deviceInfo.getId() > 0) {
                item.put("enterprise_address",deviceInfo.getDeviceAddress());
                item.put("device_lat_lon",deviceInfo.getLatLon());
                item.put("device_monitor",1);
            }else{
                item.put("enterprise_address",enterprise.getAddress());
                item.put("device_lat_lon","");
                item.put("device_monitor",0);
            }
            item.put("id",eId);
            item.put("parent_id",enterprise.getParentId());
            item.put("enterprise_name",enterprise.getName());
            item.put("lat_lon",enterprise.getLatLon());

            items.add(item);
        }
        data.put("items",items);

        return success(data);
    }

    /**
     * 获取企业管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:enterprise:list')")
    @GetMapping(value = "/getAlls/{id}")
    public AjaxResult getAllInfo(@PathVariable("id") Integer id)
    {
        return success(enterpriseService.selectEnterpriseByParentAndSelfId(id));
    }
    /**
     * 获取企业管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:enterprise:list')")
    @GetMapping(value = "/getParent")
    public AjaxResult getParent()
    {
        return success(enterpriseService.selectEnterpriseByZero());
    }

    ///**
    // * 导出企业管理列表
    // */
    //@PreAuthorize("@ss.hasPermi('suyuan:enterprise:export')")
    //@Log(title = "企业管理", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //public void export(HttpServletResponse response, Enterprise enterprise)
    //{
    //    List<Enterprise> list = enterpriseService.selectEnterpriseList(enterprise);
    //    ExcelUtil<Enterprise> util = new ExcelUtil<Enterprise>(Enterprise.class);
    //    util.exportExcel(response, list, "企业管理数据");
    //}

    /**
     * 获取企业管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('suyuan:enterprise:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(enterpriseService.selectEnterpriseById(id));
    }


    /**
     * 新增企业管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:enterprise:add')")
    @Log(title = "企业管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Enterprise enterprise)
    {
        // 企业名称不能重复
        Enterprise existingEnterprise = enterpriseService.selectEnterpriseByName(enterprise.getName());
        if (existingEnterprise != null) {
            return error("企业名称已存在");
        }
        return toAjax(enterpriseService.insertEnterprise(enterprise));
    }

    /**
     * 修改企业管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:enterprise:edit')")
    @Log(title = "企业管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Enterprise enterprise)
    {
        return toAjax(enterpriseService.updateEnterprise(enterprise));
    }

    /**
     * 删除企业管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:enterprise:remove')")
    @Log(title = "企业管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(enterpriseService.deleteEnterpriseByIds(ids));
    }
}
