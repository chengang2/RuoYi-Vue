package com.ruoyi.suyuan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.suyuan.mapper.ProduceProcessStepMapper;
import com.ruoyi.suyuan.domain.ProduceProcessStep;
import com.ruoyi.suyuan.service.IProduceProcessStepService;

/**
 * 生产过程步骤管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
@Service
public class ProduceProcessStepServiceImpl implements IProduceProcessStepService 
{
    @Autowired
    private ProduceProcessStepMapper produceProcessStepMapper;

    /**
     * 查询生产过程步骤管理
     * 
     * @param id 生产过程步骤管理主键
     * @return 生产过程步骤管理
     */
    @Override
    public ProduceProcessStep selectProduceProcessStepById(Integer id)
    {
        return produceProcessStepMapper.selectProduceProcessStepById(id);
    }

    /**
     * 查询生产过程步骤管理列表
     * 
     * @param produceProcessStep 生产过程步骤管理
     * @return 生产过程步骤管理
     */
    @Override
    public List<ProduceProcessStep> selectProduceProcessStepList(ProduceProcessStep produceProcessStep)
    {
        return produceProcessStepMapper.selectProduceProcessStepList(produceProcessStep);
    }

    /**
     * 新增生产过程步骤管理
     * 
     * @param produceProcessStep 生产过程步骤管理
     * @return 结果
     */
    @Override
    public int insertProduceProcessStep(ProduceProcessStep produceProcessStep)
    {
        produceProcessStep.setCreateTime(DateUtils.getNowDate());
        return produceProcessStepMapper.insertProduceProcessStep(produceProcessStep);
    }

    /**
     * 修改生产过程步骤管理
     * 
     * @param produceProcessStep 生产过程步骤管理
     * @return 结果
     */
    @Override
    public int updateProduceProcessStep(ProduceProcessStep produceProcessStep)
    {
        produceProcessStep.setUpdateTime(DateUtils.getNowDate());
        return produceProcessStepMapper.updateProduceProcessStep(produceProcessStep);
    }

    /**
     * 批量删除生产过程步骤管理
     * 
     * @param ids 需要删除的生产过程步骤管理主键
     * @return 结果
     */
    @Override
    public int deleteProduceProcessStepByIds(Integer[] ids)
    {
        return produceProcessStepMapper.deleteProduceProcessStepByIds(ids);
    }

    /**
     * 删除生产过程步骤管理信息
     * 
     * @param id 生产过程步骤管理主键
     * @return 结果
     */
    @Override
    public int deleteProduceProcessStepById(Integer id)
    {
        return produceProcessStepMapper.deleteProduceProcessStepById(id);
    }

    @Override
    public int deleteProduceProcessStepByPid(Integer produceProcessId) {
        return produceProcessStepMapper.deleteProduceProcessStepByPid(produceProcessId);
    }

    @Override
    public int deleteProduceProcessStepByPids(Integer[] produceProcessIds) {
        return produceProcessStepMapper.deleteProduceProcessStepByPids(produceProcessIds);
    }
}
