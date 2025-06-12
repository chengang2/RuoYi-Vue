package com.ruoyi.suyuan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.suyuan.mapper.TDetectMapper;
import com.ruoyi.suyuan.domain.TDetect;
import com.ruoyi.suyuan.service.ITDetectService;

/**
 * 检测管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-12
 */
@Service
public class TDetectServiceImpl implements ITDetectService 
{
    @Autowired
    private TDetectMapper tDetectMapper;

    /**
     * 查询检测管理
     * 
     * @param id 检测管理主键
     * @return 检测管理
     */
    @Override
    public TDetect selectTDetectById(Long id)
    {
        return tDetectMapper.selectTDetectById(id);
    }

    /**
     * 查询检测管理列表
     * 
     * @param tDetect 检测管理
     * @return 检测管理
     */
    @Override
    public List<TDetect> selectTDetectList(TDetect tDetect)
    {
        return tDetectMapper.selectTDetectList(tDetect);
    }

    /**
     * 新增检测管理
     * 
     * @param tDetect 检测管理
     * @return 结果
     */
    @Override
    public int insertTDetect(TDetect tDetect)
    {
        tDetect.setCreateTime(DateUtils.getNowDate());
        return tDetectMapper.insertTDetect(tDetect);
    }

    /**
     * 修改检测管理
     * 
     * @param tDetect 检测管理
     * @return 结果
     */
    @Override
    public int updateTDetect(TDetect tDetect)
    {
        tDetect.setUpdateTime(DateUtils.getNowDate());
        return tDetectMapper.updateTDetect(tDetect);
    }

    /**
     * 批量删除检测管理
     * 
     * @param ids 需要删除的检测管理主键
     * @return 结果
     */
    @Override
    public int deleteTDetectByIds(Long[] ids)
    {
        return tDetectMapper.deleteTDetectByIds(ids);
    }

    /**
     * 删除检测管理信息
     * 
     * @param id 检测管理主键
     * @return 结果
     */
    @Override
    public int deleteTDetectById(Long id)
    {
        return tDetectMapper.deleteTDetectById(id);
    }
}
