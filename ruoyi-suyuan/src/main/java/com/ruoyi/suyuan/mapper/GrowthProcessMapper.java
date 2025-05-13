package com.ruoyi.suyuan.mapper;

import java.util.List;
import com.ruoyi.suyuan.domain.GrowthProcess;
import com.ruoyi.suyuan.domain.GrowthProcessList;

/**
 * 成长过程管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
public interface GrowthProcessMapper 
{
    /**
     * 查询成长过程管理
     * 
     * @param id 成长过程管理主键
     * @return 成长过程管理
     */
    public GrowthProcess selectGrowthProcessById(Integer id);

    public GrowthProcess selectGrowthProcess(GrowthProcess growthProcess);
    /**
     * 查询成长过程管理列表
     * 
     * @param growthProcess 成长过程管理
     * @return 成长过程管理集合
     */
    public List<GrowthProcess> selectGrowthProcessList(GrowthProcessList growthProcess);

    /**
     * 新增成长过程管理
     * 
     * @param growthProcess 成长过程管理
     * @return 结果
     */
    public int insertGrowthProcess(GrowthProcess growthProcess);

    /**
     * 修改成长过程管理
     * 
     * @param growthProcess 成长过程管理
     * @return 结果
     */
    public int updateGrowthProcess(GrowthProcess growthProcess);

    /**
     * 删除成长过程管理
     * 
     * @param id 成长过程管理主键
     * @return 结果
     */
    public int deleteGrowthProcessById(Integer id);

    /**
     * 批量删除成长过程管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGrowthProcessByIds(Integer[] ids);
}
