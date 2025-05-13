package com.ruoyi.suyuan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.suyuan.domain.GrowthProcessList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.suyuan.mapper.GrowthProcessMapper;
import com.ruoyi.suyuan.domain.GrowthProcess;
import com.ruoyi.suyuan.service.IGrowthProcessService;

/**
 * 成长过程管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
@Service
public class GrowthProcessServiceImpl implements IGrowthProcessService 
{
    @Autowired
    private GrowthProcessMapper growthProcessMapper;

    /**
     * 查询成长过程管理
     * 
     * @param id 成长过程管理主键
     * @return 成长过程管理
     */
    @Override
    public GrowthProcess selectGrowthProcessById(Integer id)
    {
        return growthProcessMapper.selectGrowthProcessById(id);
    }

    @Override
    public GrowthProcess selectGrowthProcess(GrowthProcess growthProcess) {
        return growthProcessMapper.selectGrowthProcess(growthProcess);
    }

    /**
     * 查询成长过程管理列表
     * 
     * @param growthProcess 成长过程管理
     * @return 成长过程管理
     */
    @Override
    public List<GrowthProcess> selectGrowthProcessList(GrowthProcessList growthProcess)
    {
        return growthProcessMapper.selectGrowthProcessList(growthProcess);
    }

    /**
     * 新增成长过程管理
     * 
     * @param growthProcess 成长过程管理
     * @return 结果
     */
    @Override
    public int insertGrowthProcess(GrowthProcess growthProcess)
    {
        growthProcess.setCreateTime(DateUtils.getNowDate());
        return growthProcessMapper.insertGrowthProcess(growthProcess);
    }

    /**
     * 修改成长过程管理
     * 
     * @param growthProcess 成长过程管理
     * @return 结果
     */
    @Override
    public int updateGrowthProcess(GrowthProcess growthProcess)
    {
        growthProcess.setUpdateTime(DateUtils.getNowDate());
        return growthProcessMapper.updateGrowthProcess(growthProcess);
    }

    /**
     * 批量删除成长过程管理
     * 
     * @param ids 需要删除的成长过程管理主键
     * @return 结果
     */
    @Override
    public int deleteGrowthProcessByIds(Integer[] ids)
    {
        return growthProcessMapper.deleteGrowthProcessByIds(ids);
    }

    /**
     * 删除成长过程管理信息
     * 
     * @param id 成长过程管理主键
     * @return 结果
     */
    @Override
    public int deleteGrowthProcessById(Integer id)
    {
        return growthProcessMapper.deleteGrowthProcessById(id);
    }
}
