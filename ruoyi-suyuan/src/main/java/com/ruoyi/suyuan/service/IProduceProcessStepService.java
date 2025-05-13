package com.ruoyi.suyuan.service;

import java.util.List;
import com.ruoyi.suyuan.domain.ProduceProcessStep;

/**
 * 生产过程步骤管理Service接口
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
public interface IProduceProcessStepService 
{
    /**
     * 查询生产过程步骤管理
     * 
     * @param id 生产过程步骤管理主键
     * @return 生产过程步骤管理
     */
    public ProduceProcessStep selectProduceProcessStepById(Integer id);

    /**
     * 查询生产过程步骤管理列表
     * 
     * @param produceProcessStep 生产过程步骤管理
     * @return 生产过程步骤管理集合
     */
    public List<ProduceProcessStep> selectProduceProcessStepList(ProduceProcessStep produceProcessStep);

    /**
     * 新增生产过程步骤管理
     * 
     * @param produceProcessStep 生产过程步骤管理
     * @return 结果
     */
    public int insertProduceProcessStep(ProduceProcessStep produceProcessStep);

    /**
     * 修改生产过程步骤管理
     * 
     * @param produceProcessStep 生产过程步骤管理
     * @return 结果
     */
    public int updateProduceProcessStep(ProduceProcessStep produceProcessStep);

    /**
     * 批量删除生产过程步骤管理
     * 
     * @param ids 需要删除的生产过程步骤管理主键集合
     * @return 结果
     */
    public int deleteProduceProcessStepByIds(Integer[] ids);
    /**
     * 删除生产过程步骤管理信息
     * 
     * @param id 生产过程步骤管理主键
     * @return 结果
     */
    public int deleteProduceProcessStepById(Integer id);

    public int deleteProduceProcessStepByPid(Integer produceProcessId);

    public int deleteProduceProcessStepByPids(Integer[] produceProcessIds);
}
