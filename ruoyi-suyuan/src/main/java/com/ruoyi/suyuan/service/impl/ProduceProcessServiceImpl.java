package com.ruoyi.suyuan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.suyuan.domain.ProduceProcessList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.suyuan.mapper.ProduceProcessMapper;
import com.ruoyi.suyuan.domain.ProduceProcess;
import com.ruoyi.suyuan.service.IProduceProcessService;

/**
 * 生产过程管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
@Service
public class ProduceProcessServiceImpl implements IProduceProcessService 
{
    @Autowired
    private ProduceProcessMapper produceProcessMapper;

    /**
     * 查询生产过程管理
     * 
     * @param id 生产过程管理主键
     * @return 生产过程管理
     */
    @Override
    public ProduceProcess selectProduceProcessById(Integer id)
    {
        return produceProcessMapper.selectProduceProcessById(id);
    }

    @Override
    public ProduceProcess selectProduceProcess(ProduceProcess produceProcess) {
        return produceProcessMapper.selectProduceProcess(produceProcess);
    }

    /**
     * 查询生产过程管理列表
     * 
     * @param produceProcess 生产过程管理
     * @return 生产过程管理
     */
    @Override
    public List<ProduceProcess> selectProduceProcessList(ProduceProcessList produceProcess)
    {
        return produceProcessMapper.selectProduceProcessList(produceProcess);
    }

    /**
     * 新增生产过程管理
     * 
     * @param produceProcess 生产过程管理
     * @return 结果
     */
    @Override
    public int insertProduceProcess(ProduceProcess produceProcess)
    {
        produceProcess.setCreateTime(DateUtils.getNowDate());
        return produceProcessMapper.insertProduceProcess(produceProcess);
    }

    /**
     * 修改生产过程管理
     * 
     * @param produceProcess 生产过程管理
     * @return 结果
     */
    @Override
    public int updateProduceProcess(ProduceProcess produceProcess)
    {
        produceProcess.setUpdateTime(DateUtils.getNowDate());
        return produceProcessMapper.updateProduceProcess(produceProcess);
    }

    /**
     * 批量删除生产过程管理
     * 
     * @param ids 需要删除的生产过程管理主键
     * @return 结果
     */
    @Override
    public int deleteProduceProcessByIds(Integer[] ids)
    {
        return produceProcessMapper.deleteProduceProcessByIds(ids);
    }

    /**
     * 删除生产过程管理信息
     * 
     * @param id 生产过程管理主键
     * @return 结果
     */
    @Override
    public int deleteProduceProcessById(Integer id)
    {
        return produceProcessMapper.deleteProduceProcessById(id);
    }
}
