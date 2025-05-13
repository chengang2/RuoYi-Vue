package com.ruoyi.suyuan.controller;

import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.suyuan.domain.*;
import com.ruoyi.suyuan.service.IEnterpriseService;
import com.ruoyi.suyuan.service.IGrowthProcessStepService;
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
import com.ruoyi.suyuan.service.IGrowthProcessService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 成长过程管理Controller
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
@Api(tags = "溯源模块-成长过程管理")
@RestController
@RequestMapping("/suyuan/growthprocess")
public class GrowthProcessController extends BaseController
{
    @Autowired
    private IGrowthProcessService growthProcessService;
    @Autowired
    private IGrowthProcessStepService growthProcessStepService;
    @Autowired
    private IEnterpriseService enterpriseService;

    /**
     * 查询成长过程管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:growthprocess:list')")
    @ApiOperation("获取成长过程列表,原接口: /growthProcess/getAllByPage")
    @GetMapping("/list")
    public TableDataInfo list(GrowthProcess growthProcess)
    {
        Integer enterpriseId = growthProcess.getEnterpriseId();
        List<Integer> enterpriseIds = new ArrayList<>();
        List<Enterprise> enterprises = enterpriseService.selectEnterpriseByParentAndSelfId(enterpriseId);
        for (Enterprise enterprise : enterprises) {
            Integer id = enterprise.getId();
            enterpriseIds.add(id);
        }

        if(!enterpriseIds.isEmpty()){
            GrowthProcessList growthProcessList = new GrowthProcessList();
            growthProcessList.setEnterpriseIds(enterpriseIds);
            List<GrowthProcess> list = growthProcessService.selectGrowthProcessList(growthProcessList);
            //startPage();
            List<Map<String,Object>> growthProcessLists = new ArrayList<>();
            for (GrowthProcess growthProcess1 : list) {
                Map<String,Object> map = new HashMap<>();
                Integer enterpriseId1 = growthProcess1.getEnterpriseId();
                String enterpriseName = "";
                if (enterpriseId1 != null) {
                    Enterprise enterprise = enterpriseService.selectEnterpriseById(enterpriseId1);
                    enterpriseName = enterprise.getName();
                }
                map.put("id",growthProcess1.getId());
                map.put("name",growthProcess1.getName());
                map.put("enterpriseId",enterpriseId1);
                map.put("enterpriseName",enterpriseName);
                map.put("productCategoryId",growthProcess1.getProductCategoryId());
                map.put("createTime", DateFormatUtil.format(growthProcess1.getCreateTime()));
                map.put("updateTime", DateFormatUtil.format(growthProcess1.getUpdateTime()));

                growthProcessLists.add(map);
            }

            return getDataTable(growthProcessLists);

        }else{
            List<Map<String,Object>> prowthProcessList = new ArrayList<>();
            return getDataTable(prowthProcessList);
        }

    }

    @PreAuthorize("@ss.hasPermi('suyuan:growthprocess:list')")
    @ApiOperation("获取成长过程列表,原接口: /growthProcess/getAll")
    @GetMapping("/getAll")
    public AjaxResult getAll(GrowthProcess growthProcess)
    {
        Map<String, Object> data = new HashMap<>();

        Integer enterpriseId = growthProcess.getEnterpriseId();
        List<Integer> enterpriseIds = new ArrayList<>();
        List<Enterprise> enterprises = enterpriseService.selectEnterpriseByParentAndSelfId(enterpriseId);
        for (Enterprise enterprise : enterprises) {
            Integer id = enterprise.getId();
            enterpriseIds.add(id);
        }

        if(!enterpriseIds.isEmpty()) {
            GrowthProcessList growthProcessList = new GrowthProcessList();
            growthProcessList.setEnterpriseIds(enterpriseIds);
            List<GrowthProcess> list = growthProcessService.selectGrowthProcessList(growthProcessList);
            List<Map<String, Object>> growthProcessLists = new ArrayList<>();
            for (GrowthProcess growthProcess1 : list) {
                Map<String,Object> map = new HashMap<>();
                Integer enterpriseId1 = growthProcess1.getEnterpriseId();
                String enterpriseName = "";
                if (enterpriseId1 != null) {
                    Enterprise enterprise = enterpriseService.selectEnterpriseById(enterpriseId1);
                    enterpriseName = enterprise.getName();
                }
                map.put("id",growthProcess1.getId());
                map.put("name",growthProcess1.getName());
                map.put("enterpriseId",enterpriseId1);
                map.put("enterpriseName",enterpriseName);
                map.put("productCategoryId",growthProcess1.getProductCategoryId());
                map.put("createTime", DateFormatUtil.format(growthProcess1.getCreateTime()));
                map.put("updateTime", DateFormatUtil.format(growthProcess1.getUpdateTime()));

                growthProcessLists.add(map);
            }
            data.put("items",growthProcessLists);

            return success(data);
        }else{
            return error("企业id不能为空");
        }
    }

    ///**
    // * 导出成长过程管理列表
    // */
    //@PreAuthorize("@ss.hasPermi('suyuan:growthprocess:export')")
    //@Log(title = "成长过程管理", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //public void export(HttpServletResponse response, GrowthProcess growthProcess)
    //{
    //    List<GrowthProcess> list = growthProcessService.selectGrowthProcessList(growthProcess);
    //    ExcelUtil<GrowthProcess> util = new ExcelUtil<GrowthProcess>(GrowthProcess.class);
    //    util.exportExcel(response, list, "成长过程管理数据");
    //}

    /**
     * 获取成长过程管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('suyuan:growthprocess:query')")
    @ApiOperation("获取成长过程详情,原接口: /growthProcess/get")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        Map<String,Object> data = new HashMap<>();

        GrowthProcess growthProcess = growthProcessService.selectGrowthProcessById(id);
        Integer enterpriseId = growthProcess.getEnterpriseId();
        Enterprise enterprise = enterpriseService.selectEnterpriseById(enterpriseId);
        String enterpriseName = enterprise.getName();

        GrowthProcessStep growthProcessStep = new GrowthProcessStep();
        growthProcessStep.setGrowthProcessId(id);
        List<GrowthProcessStep> growthProcessSteps = growthProcessStepService.selectGrowthProcessStepList(growthProcessStep);
        data.put("id",growthProcess.getId());
        data.put("name",growthProcess.getName());
        data.put("enterpriseId",enterpriseId);
        data.put("enterpriseName",enterpriseName);
        data.put("productCategoryId",growthProcess.getProductCategoryId());
        data.put("items",growthProcessSteps);
        data.put("createTime", DateFormatUtil.format(growthProcess.getCreateTime()));
        data.put("updateTime", DateFormatUtil.format(growthProcess.getUpdateTime()));

        return success(data);
    }

    /**
     * 新增成长过程管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:growthprocess:add')")
    @Log(title = "成长过程管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增成长过程,原接口: /growthProcess/create")
    @PostMapping
    public AjaxResult add(@RequestBody GrowthProcessVO growthProcessVO)
    {
        GrowthProcess growthProcess = new GrowthProcess();
        growthProcess.setName(growthProcessVO.getName());
        growthProcess.setEnterpriseId(growthProcessVO.getEnterpriseId());
        GrowthProcess growthProcess1 = growthProcessService.selectGrowthProcess(growthProcess);
        if (growthProcess1 != null) {
            return error("生长流程已存在,请更改");
        }
        growthProcess.setProductCategoryId(growthProcessVO.getProductCategoryId());
        int i = growthProcessService.insertGrowthProcess(growthProcess);
        if(i > 0){
            Integer gId = growthProcess.getId();
            GrowthProcessStep[] items = growthProcessVO.getItems();
            if(items != null && items.length > 0){
                for (int j = 0; j < items.length; j++) {
                    GrowthProcessStep item = items[j];
                    GrowthProcessStep growthProcessStep = new GrowthProcessStep();
                    growthProcessStep.setGrowthProcessId(gId);
                    growthProcessStep.setName(item.getName());
                    growthProcessStep.setPicture(item.getPicture());

                    growthProcessStepService.insertGrowthProcessStep(growthProcessStep);
                }
            }
            return success("新增成功");
        }else{
            return error("新增失败");
        }

    }

    /**
     * 修改成长过程管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:growthprocess:edit')")
    @Log(title = "成长过程管理", businessType = BusinessType.UPDATE)
    @ApiOperation("修改成长过程,原接口: /growthProcess/update")
    @PutMapping
    public AjaxResult edit(@RequestBody GrowthProcessVO growthProcessVO)
    {
        Integer id = growthProcessVO.getId();

        GrowthProcess growthProcess = new GrowthProcess();
        growthProcess.setName(growthProcessVO.getName());
        growthProcess.setEnterpriseId(growthProcessVO.getEnterpriseId());
        GrowthProcess growthProcess1 = growthProcessService.selectGrowthProcess(growthProcess);
        if (growthProcess1 != null && !Objects.equals(growthProcess1.getId(), id)) {
            return error("生长流程已存在,请更改");
        }
        growthProcess.setId(id);
        growthProcess.setProductCategoryId(growthProcessVO.getProductCategoryId());
        int i = growthProcessService.updateGrowthProcess(growthProcess);
        if (i > 0){
            GrowthProcessStep[] items = growthProcessVO.getItems();
            if(items != null && items.length > 0){
                int i1 = growthProcessStepService.deleteGrowthProcessStepByGid(id);
                if(i1 > 0){
                    for (GrowthProcessStep item : items) {
                        GrowthProcessStep growthProcessStep = new GrowthProcessStep();
                        growthProcessStep.setGrowthProcessId(id);
                        growthProcessStep.setName(item.getName());
                        growthProcessStep.setPicture(item.getPicture());
                        growthProcessStepService.insertGrowthProcessStep(growthProcessStep);
                    }
                }

            }
            return success("修改成功");
        }else{
            return error("修改失败");
        }
    }

    /**
     * 删除成长过程管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:growthprocess:remove')")
    @Log(title = "成长过程管理", businessType = BusinessType.DELETE)
    @ApiOperation("删除成长过程,多个id之间用逗号隔开。原接口: /growthProcess/delete")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        if (ids != null && ids.length > 0) {
            int i = growthProcessStepService.deleteGrowthProcessStepByGids(ids);
            if (i > 0) {
                return toAjax(growthProcessService.deleteGrowthProcessByIds(ids));
            }else{
                return error("删除失败");
            }
        }else{
            return error("id不能为空");
        }

    }
}
