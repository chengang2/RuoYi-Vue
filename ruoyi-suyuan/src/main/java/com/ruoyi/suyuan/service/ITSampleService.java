package com.ruoyi.suyuan.service;

import java.util.List;
import com.ruoyi.suyuan.domain.TSample;

/**
 * 样品管理Service接口
 * 
 * @author ruoyi
 * @date 2025-06-12
 */
public interface ITSampleService 
{
    /**
     * 查询样品管理
     * 
     * @param id 样品管理主键
     * @return 样品管理
     */
    public TSample selectTSampleById(Long id);

    /**
     * 查询样品管理列表
     * 
     * @param tSample 样品管理
     * @return 样品管理集合
     */
    public List<TSample> selectTSampleList(TSample tSample);

    /**
     * 新增样品管理
     * 
     * @param tSample 样品管理
     * @return 结果
     */
    public int insertTSample(TSample tSample);

    /**
     * 修改样品管理
     * 
     * @param tSample 样品管理
     * @return 结果
     */
    public int updateTSample(TSample tSample);

    /**
     * 批量删除样品管理
     * 
     * @param ids 需要删除的样品管理主键集合
     * @return 结果
     */
    public int deleteTSampleByIds(Long[] ids);

    /**
     * 删除样品管理信息
     * 
     * @param id 样品管理主键
     * @return 结果
     */
    public int deleteTSampleById(Long id);
}
