package com.ruoyi.suyuan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.suyuan.mapper.TSampleMapper;
import com.ruoyi.suyuan.domain.TSample;
import com.ruoyi.suyuan.service.ITSampleService;

/**
 * 样品管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-12
 */
@Service
public class TSampleServiceImpl implements ITSampleService 
{
    @Autowired
    private TSampleMapper tSampleMapper;

    /**
     * 查询样品管理
     * 
     * @param id 样品管理主键
     * @return 样品管理
     */
    @Override
    public TSample selectTSampleById(Long id)
    {
        return tSampleMapper.selectTSampleById(id);
    }

    /**
     * 查询样品管理列表
     * 
     * @param tSample 样品管理
     * @return 样品管理
     */
    @Override
    public List<TSample> selectTSampleList(TSample tSample)
    {
        return tSampleMapper.selectTSampleList(tSample);
    }

    /**
     * 新增样品管理
     * 
     * @param tSample 样品管理
     * @return 结果
     */
    @Override
    public int insertTSample(TSample tSample)
    {
        tSample.setCreateTime(DateUtils.getNowDate());
        return tSampleMapper.insertTSample(tSample);
    }

    /**
     * 修改样品管理
     * 
     * @param tSample 样品管理
     * @return 结果
     */
    @Override
    public int updateTSample(TSample tSample)
    {
        tSample.setUpdateTime(DateUtils.getNowDate());
        return tSampleMapper.updateTSample(tSample);
    }

    /**
     * 批量删除样品管理
     * 
     * @param ids 需要删除的样品管理主键
     * @return 结果
     */
    @Override
    public int deleteTSampleByIds(Long[] ids)
    {
        return tSampleMapper.deleteTSampleByIds(ids);
    }

    /**
     * 删除样品管理信息
     * 
     * @param id 样品管理主键
     * @return 结果
     */
    @Override
    public int deleteTSampleById(Long id)
    {
        return tSampleMapper.deleteTSampleById(id);
    }
}
