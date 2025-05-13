package com.ruoyi.suyuan.service;

import java.util.List;
import com.ruoyi.suyuan.domain.GrowthProcessStep;

/**
 * 成长过程步骤管理Service接口
 * 
 * @author chengang
 * @date 2025-05-13
 */
public interface IGrowthProcessStepService 
{
    /**
     * 查询成长过程步骤管理
     * 
     * @param id 成长过程步骤管理主键
     * @return 成长过程步骤管理
     */
    public GrowthProcessStep selectGrowthProcessStepById(Integer id);

    /**
     * 查询成长过程步骤管理列表
     * 
     * @param growthProcessStep 成长过程步骤管理
     * @return 成长过程步骤管理集合
     */
    public List<GrowthProcessStep> selectGrowthProcessStepList(GrowthProcessStep growthProcessStep);

    /**
     * 新增成长过程步骤管理
     * 
     * @param growthProcessStep 成长过程步骤管理
     * @return 结果
     */
    public int insertGrowthProcessStep(GrowthProcessStep growthProcessStep);

    /**
     * 修改成长过程步骤管理
     * 
     * @param growthProcessStep 成长过程步骤管理
     * @return 结果
     */
    public int updateGrowthProcessStep(GrowthProcessStep growthProcessStep);

    /**
     * 批量删除成长过程步骤管理
     * 
     * @param ids 需要删除的成长过程步骤管理主键集合
     * @return 结果
     */
    public int deleteGrowthProcessStepByIds(Integer[] ids);

    /**
     * 删除成长过程步骤管理信息
     * 
     * @param id 成长过程步骤管理主键
     * @return 结果
     */
    public int deleteGrowthProcessStepById(Integer id);

    /**
     * 删除成长过程步骤管理
     *
     * @param growthProcessId 成长过程步骤管理主键
     * @return 结果
     */
    public int deleteGrowthProcessStepByGid(Integer growthProcessId);

    /**
     * 批量删除成长过程步骤管理
     *
     * @param growthProcessIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGrowthProcessStepByGids(Integer[] growthProcessIds);
}
