package com.ruoyi.suyuan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.suyuan.mapper.GrowthProcessStepMapper;
import com.ruoyi.suyuan.domain.GrowthProcessStep;
import com.ruoyi.suyuan.service.IGrowthProcessStepService;

/**
 * 成长过程步骤管理Service业务层处理
 * 
 * @author chengang
 * @date 2025-05-13
 */
@Service
public class GrowthProcessStepServiceImpl implements IGrowthProcessStepService 
{
    @Autowired
    private GrowthProcessStepMapper growthProcessStepMapper;

    /**
     * 查询成长过程步骤管理
     * 
     * @param id 成长过程步骤管理主键
     * @return 成长过程步骤管理
     */
    @Override
    public GrowthProcessStep selectGrowthProcessStepById(Integer id)
    {
        return growthProcessStepMapper.selectGrowthProcessStepById(id);
    }

    /**
     * 查询成长过程步骤管理列表
     * 
     * @param growthProcessStep 成长过程步骤管理
     * @return 成长过程步骤管理
     */
    @Override
    public List<GrowthProcessStep> selectGrowthProcessStepList(GrowthProcessStep growthProcessStep)
    {
        return growthProcessStepMapper.selectGrowthProcessStepList(growthProcessStep);
    }

    /**
     * 新增成长过程步骤管理
     * 
     * @param growthProcessStep 成长过程步骤管理
     * @return 结果
     */
    @Override
    public int insertGrowthProcessStep(GrowthProcessStep growthProcessStep)
    {
        growthProcessStep.setCreateTime(DateUtils.getNowDate());
        return growthProcessStepMapper.insertGrowthProcessStep(growthProcessStep);
    }

    /**
     * 修改成长过程步骤管理
     * 
     * @param growthProcessStep 成长过程步骤管理
     * @return 结果
     */
    @Override
    public int updateGrowthProcessStep(GrowthProcessStep growthProcessStep)
    {
        growthProcessStep.setUpdateTime(DateUtils.getNowDate());
        return growthProcessStepMapper.updateGrowthProcessStep(growthProcessStep);
    }

    /**
     * 批量删除成长过程步骤管理
     * 
     * @param ids 需要删除的成长过程步骤管理主键
     * @return 结果
     */
    @Override
    public int deleteGrowthProcessStepByIds(Integer[] ids)
    {
        return growthProcessStepMapper.deleteGrowthProcessStepByIds(ids);
    }

    /**
     * 删除成长过程步骤管理信息
     * 
     * @param id 成长过程步骤管理主键
     * @return 结果
     */
    @Override
    public int deleteGrowthProcessStepById(Integer id)
    {
        return growthProcessStepMapper.deleteGrowthProcessStepById(id);
    }

    @Override
    public int deleteGrowthProcessStepByGid(Integer growthProcessId) {
        return growthProcessStepMapper.deleteGrowthProcessStepByGid(growthProcessId);
    }

    @Override
    public int deleteGrowthProcessStepByGids(Integer[] growthProcessIds) {
        return growthProcessStepMapper.deleteGrowthProcessStepByGids(growthProcessIds);
    }
}
