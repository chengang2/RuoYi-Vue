package com.ruoyi.suyuan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.suyuan.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.suyuan.mapper.OperateLogMapper;
import com.ruoyi.suyuan.service.IOperateLogService;

/**
 * 操作日志管理Service业务层处理
 * 
 * @author chengang
 * @date 2025-05-15
 */
@Service
public class OperateLogServiceImpl implements IOperateLogService 
{
    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 查询操作日志管理
     * 
     * @param id 操作日志管理主键
     * @return 操作日志管理
     */
    @Override
    public OperateLog selectOperateLogById(Integer id)
    {
        return operateLogMapper.selectOperateLogById(id);
    }

    /**
     * 查询操作日志管理列表
     * 
     * @param operateLogList 操作日志管理
     * @return 操作日志管理
     */
    @Override
    public List<OperateLog> selectOperateLogList(OperateLogList operateLogList)
    {
        return operateLogMapper.selectOperateLogList(operateLogList);
    }

    /**
     * 新增操作日志管理
     * 
     * @param operateLog 操作日志管理
     * @return 结果
     */
    @Override
    public int insertOperateLog(OperateLog operateLog)
    {
        operateLog.setCreateTime(DateUtils.getNowDate());
        return operateLogMapper.insertOperateLog(operateLog);
    }

    /**
     * 修改操作日志管理
     * 
     * @param operateLog 操作日志管理
     * @return 结果
     */
    @Override
    public int updateOperateLog(OperateLog operateLog)
    {
        operateLog.setUpdateTime(DateUtils.getNowDate());
        return operateLogMapper.updateOperateLog(operateLog);
    }

    /**
     * 批量删除操作日志管理
     * 
     * @param ids 需要删除的操作日志管理主键
     * @return 结果
     */
    @Override
    public int deleteOperateLogByIds(Integer[] ids)
    {
        return operateLogMapper.deleteOperateLogByIds(ids);
    }

    /**
     * 删除操作日志管理信息
     * 
     * @param id 操作日志管理主键
     * @return 结果
     */
    @Override
    public int deleteOperateLogById(Integer id)
    {
        return operateLogMapper.deleteOperateLogById(id);
    }

    @Override
    public List<OperateLogScoreMonth> selectScoreByMonth() {
        return operateLogMapper.selectScoreByMonth();
    }

    @Override
    public List<OperateLogScoreProduct> selectScoreByProduct() {
        return operateLogMapper.selectScoreByProduct();
    }

    @Override
    public List<OperateLogScoreProductMonth> selectScoreByProductAndMonth() {
        return operateLogMapper.selectScoreByProductAndMonth();
    }
}
