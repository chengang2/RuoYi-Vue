package com.ruoyi.suyuan.controller;

import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.suyuan.domain.*;
import com.ruoyi.suyuan.service.IEnterpriseService;
import com.ruoyi.suyuan.service.IProduceProcessStepService;
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
import com.ruoyi.suyuan.service.IProduceProcessService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 生产过程管理Controller
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
@Api(tags = "溯源模块-生产过程管理")
@RestController
@RequestMapping("/suyuan/produceprocess")
public class ProduceProcessController extends BaseController
{
    @Autowired
    private IProduceProcessService produceProcessService;
    @Autowired
    private IProduceProcessStepService produceProcessStepService;
    @Autowired
    private IEnterpriseService enterpriseService;

    /**
     * 查询生产过程管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:produceprocess:list')")
    @ApiOperation("获取生产过程列表,原接口: /produceProcess/getAllByPage")
    @GetMapping("/list")
    public TableDataInfo list(ProduceProcess produceProcess)
    {
        Integer enterpriseId = produceProcess.getEnterpriseId();
        List<Integer> enterpriseIds = new ArrayList<>();
        List<Enterprise> enterprises = enterpriseService.selectEnterpriseByParentAndSelfId(enterpriseId);
        for (Enterprise enterprise : enterprises) {
            Integer id = enterprise.getId();
            enterpriseIds.add(id);
        }
        if(!enterpriseIds.isEmpty()){
            ProduceProcessList produceProcessList = new ProduceProcessList();
            produceProcessList.setEnterpriseIds(enterpriseIds);
            startPage();
            List<ProduceProcess> list = produceProcessService.selectProduceProcessList(produceProcessList);

            PageInfo<ProduceProcess> pageInfo = new PageInfo<>(list);

            List<Map<String,Object>> produceProcessLists = new ArrayList<>();
            for (ProduceProcess produceProcess1 : list) {
                Map<String,Object> item = new HashMap<>();
                Integer id = produceProcess1.getId();
                String name = produceProcess1.getName();
                Integer enterpriseId1 = produceProcess1.getEnterpriseId();
                String enterpriseName = "";
                if (enterpriseId1 != null) {
                    Enterprise enterprise = enterpriseService.selectEnterpriseById(enterpriseId1);
                    enterpriseName = enterprise.getName();
                }
                item.put("id", id);
                item.put("name", name);
                item.put("enterpriseId", enterpriseId1);
                item.put("enterpriseName", enterpriseName);
                item.put("productCategoryId",produceProcess1.getProductCategoryId());
                item.put("createTime", DateFormatUtil.format(produceProcess1.getCreateTime()));
                item.put("updateTime", DateFormatUtil.format(produceProcess1.getUpdateTime()));

                produceProcessLists.add(item);
            }
            // 用原始list的分页信息，返回你组装的items
            TableDataInfo rspData = new TableDataInfo();
            rspData.setCode(HttpStatus.SUCCESS);
            rspData.setMsg("查询成功");
            rspData.setRows(produceProcessLists);
            rspData.setTotal(pageInfo.getTotal());

            return rspData;
        }else{
            List<Map<String,Object>> ProduceProcessList = new ArrayList<>();
            return getDataTable(ProduceProcessList);
        }


    }

    @PreAuthorize("@ss.hasPermi('suyuan:produceprocess:list')")
    @ApiOperation("获取生产过程列表,原接口: /produceProcess/getAll")
    @GetMapping("/getAll")
    public AjaxResult getAll(ProduceProcess produceProcess)
    {
        Map<String, Object> data = new HashMap<>();

        Integer enterpriseId = produceProcess.getEnterpriseId();
        List<Integer> enterpriseIds = new ArrayList<>();
        List<Enterprise> enterprises = enterpriseService.selectEnterpriseByParentAndSelfId(enterpriseId);
        for (Enterprise enterprise : enterprises) {
            Integer id = enterprise.getId();
            enterpriseIds.add(id);
        }
        if(!enterpriseIds.isEmpty()) {
            ProduceProcessList produceProcessList = new ProduceProcessList();
            produceProcessList.setEnterpriseIds(enterpriseIds);
            List<ProduceProcess> list = produceProcessService.selectProduceProcessList(produceProcessList);
            List<Map<String, Object>> produceProcessLists = new ArrayList<>();
            for (ProduceProcess produceProcess1 : list) {
                Map<String, Object> item = new HashMap<>();
                Integer id = produceProcess1.getId();
                String name = produceProcess1.getName();
                Integer enterpriseId1 = produceProcess1.getEnterpriseId();
                String enterpriseName = "";
                if (enterpriseId1 != null) {
                    Enterprise enterprise = enterpriseService.selectEnterpriseById(enterpriseId1);
                    enterpriseName = enterprise.getName();
                }
                item.put("id", id);
                item.put("name", name);
                item.put("enterpriseId", enterpriseId1);
                item.put("enterpriseName", enterpriseName);
                item.put("productCategoryId", produceProcess1.getProductCategoryId());
                item.put("createTime", DateFormatUtil.format(produceProcess1.getCreateTime()));
                item.put("updateTime", DateFormatUtil.format(produceProcess1.getUpdateTime()));

                produceProcessLists.add(item);
            }

            data.put("items",produceProcessLists);

            return success(data);
        }else{
            return error("企业id不能为空");
        }

    }

    ///**
    // * 导出生产过程管理列表
    // */
    //@PreAuthorize("@ss.hasPermi('suyuan:produceprocess:export')")
    //@Log(title = "生产过程管理", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //public void export(HttpServletResponse response, ProduceProcess produceProcess)
    //{
    //    List<ProduceProcess> list = produceProcessService.selectProduceProcessList(produceProcess);
    //    ExcelUtil<ProduceProcess> util = new ExcelUtil<ProduceProcess>(ProduceProcess.class);
    //    util.exportExcel(response, list, "生产过程管理数据");
    //}

    /**
     * 获取生产过程管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('suyuan:produceprocess:query')")
    @ApiOperation("获取生产过程详情,原接口: /produceProcess/get")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        Map<String,Object> data = new HashMap<>();
        ProduceProcess produceProcess = produceProcessService.selectProduceProcessById(id);

        Integer enterpriseId = produceProcess.getEnterpriseId();
        Enterprise enterprise = enterpriseService.selectEnterpriseById(enterpriseId);
        String name = enterprise.getName();

        ProduceProcessStep produceProcessStep = new ProduceProcessStep();
        produceProcessStep.setProduceProcessId(id);
        List<ProduceProcessStep> produceProcessSteps = produceProcessStepService.selectProduceProcessStepList(produceProcessStep);

        data.put("id",produceProcess.getId());
        data.put("name",produceProcess.getName());
        data.put("enterpriseId",enterpriseId);
        data.put("productCategoryId",produceProcess.getProductCategoryId());
        data.put("enterpriseName",name);
        data.put("items",produceProcessSteps);
        data.put("createTime",DateFormatUtil.format(produceProcess.getCreateTime()));
        data.put("updateTime",DateFormatUtil.format(produceProcess.getUpdateTime()));

        return success(data);
    }

    /**
     * 新增生产过程管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:produceprocess:add')")
    @Log(title = "生产过程管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增生产过程,原接口: /produceProcess/create")
    @PostMapping
    public AjaxResult add(@RequestBody ProduceProcessVO produceProcessVO)
    {
        ProduceProcess produceProcess1 = new ProduceProcess();
        produceProcess1.setEnterpriseId(produceProcessVO.getEnterpriseId());
        produceProcess1.setName(produceProcessVO.getName());
        ProduceProcess produceProcess2 = produceProcessService.selectProduceProcess(produceProcess1);
        if (produceProcess2 != null) {
            return error("加工流程已存在,请更改");
        }
        ProduceProcess produceProcess = new ProduceProcess();
        produceProcess.setEnterpriseId(produceProcessVO.getEnterpriseId());
        produceProcess.setProductCategoryId(produceProcessVO.getProductCategoryId());
        produceProcess.setName(produceProcessVO.getName());
        int i = produceProcessService.insertProduceProcess(produceProcess);
        if (i > 0) {
            Integer pId = produceProcess.getId();
            ProduceProcessStep[] items = produceProcessVO.getItems();
            if(items != null && items.length > 0){
                for (int j = 0; j < items.length; j++) {
                    ProduceProcessStep item = items[j];
                    ProduceProcessStep produceProcessStep = new ProduceProcessStep();
                    produceProcessStep.setProduceProcessId(pId);
                    produceProcessStep.setName(item.getName());
                    produceProcessStep.setPicture(item.getPicture());
                    produceProcessStepService.insertProduceProcessStep(produceProcessStep);
                }
            }
            return success("添加成功");
        } else {
            return error("添加失败");
        }

    }

    /**
     * 修改生产过程管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:produceprocess:edit')")
    @Log(title = "生产过程管理", businessType = BusinessType.UPDATE)
    @ApiOperation("修改生产过程,原接口: /produceProcess/update")
    @PutMapping
    public AjaxResult edit(@RequestBody ProduceProcessVO produceProcessVO)
    {
        Integer pId = produceProcessVO.getId();

        ProduceProcess produceProcess1 = new ProduceProcess();
        produceProcess1.setEnterpriseId(produceProcessVO.getEnterpriseId());
        produceProcess1.setName(produceProcessVO.getName());
        ProduceProcess produceProcess2 = produceProcessService.selectProduceProcess(produceProcess1);
        if (produceProcess2 != null && !Objects.equals(produceProcess2.getId(), pId)) {
            return error("加工流程已存在,请更改");
        }

        ProduceProcess produceProcess = new ProduceProcess();
        produceProcess.setId(pId);
        produceProcess.setEnterpriseId(produceProcessVO.getEnterpriseId());
        produceProcess.setProductCategoryId(produceProcessVO.getProductCategoryId());
        produceProcess.setName(produceProcessVO.getName());
        int i = produceProcessService.updateProduceProcess(produceProcess);
        if (i > 0) {
            ProduceProcessStep[] items = produceProcessVO.getItems();
            if(items != null && items.length > 0){
                int i1 = produceProcessStepService.deleteProduceProcessStepByPid(pId);
                if(i1 > 0){
                    for (int j = 0; j < items.length; j++) {
                        ProduceProcessStep item = items[j];
                        ProduceProcessStep produceProcessStep = new ProduceProcessStep();
                        produceProcessStep.setProduceProcessId(pId);
                        produceProcessStep.setName(item.getName());
                        produceProcessStep.setPicture(item.getPicture());
                        produceProcessStepService.insertProduceProcessStep(produceProcessStep);
                    }
                }
            }

            return success("修改成功");
        } else {
            return error("修改失败");
        }

    }

    /**
     * 删除生产过程管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:produceprocess:remove')")
    @Log(title = "生产过程管理", businessType = BusinessType.DELETE)
    @ApiOperation("删除生产过程,多个id之间用逗号隔开。原接口: /produceProcess/delete")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        if (ids != null && ids.length > 0) {
            int i = produceProcessStepService.deleteProduceProcessStepByPids(ids);
            if (i > 0) {
                return toAjax(produceProcessService.deleteProduceProcessByIds(ids));
            }else{
                return error("删除失败");
            }
        }else{
            return error("id不能为空");
        }

    }
}
