package com.ruoyi.suyuan.service;

import java.util.List;

import com.ruoyi.suyuan.domain.*;

/**
 * 操作日志管理Service接口
 * 
 * @author chengang
 * @date 2025-05-15
 */
public interface IOperateLogService 
{
    /**
     * 查询操作日志管理
     * 
     * @param id 操作日志管理主键
     * @return 操作日志管理
     */
    public OperateLog selectOperateLogById(Integer id);

    /**
     * 查询操作日志管理列表
     * 
     * @param operateLogList 操作日志管理
     * @return 操作日志管理集合
     */
    public List<OperateLog> selectOperateLogList(OperateLogList operateLogList);

    /**
     * 新增操作日志管理
     * 
     * @param operateLog 操作日志管理
     * @return 结果
     */
    public int insertOperateLog(OperateLog operateLog);

    /**
     * 修改操作日志管理
     * 
     * @param operateLog 操作日志管理
     * @return 结果
     */
    public int updateOperateLog(OperateLog operateLog);

    /**
     * 批量删除操作日志管理
     * 
     * @param ids 需要删除的操作日志管理主键集合
     * @return 结果
     */
    public int deleteOperateLogByIds(Integer[] ids);

    /**
     * 删除操作日志管理信息
     * 
     * @param id 操作日志管理主键
     * @return 结果
     */
    public int deleteOperateLogById(Integer id);


    public List<OperateLogScoreMonth> selectScoreByMonth();

    public List<OperateLogScoreProduct> selectScoreByProduct();

    public List<OperateLogScoreProductMonth> selectScoreByProductAndMonth();
}
