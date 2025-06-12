package com.ruoyi.suyuan.mapper;

import java.util.List;
import com.ruoyi.suyuan.domain.TDetect;

/**
 * 检测管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-12
 */
public interface TDetectMapper 
{
    /**
     * 查询检测管理
     * 
     * @param id 检测管理主键
     * @return 检测管理
     */
    public TDetect selectTDetectById(Long id);

    /**
     * 查询检测管理列表
     * 
     * @param tDetect 检测管理
     * @return 检测管理集合
     */
    public List<TDetect> selectTDetectList(TDetect tDetect);

    /**
     * 新增检测管理
     * 
     * @param tDetect 检测管理
     * @return 结果
     */
    public int insertTDetect(TDetect tDetect);

    /**
     * 修改检测管理
     * 
     * @param tDetect 检测管理
     * @return 结果
     */
    public int updateTDetect(TDetect tDetect);

    /**
     * 删除检测管理
     * 
     * @param id 检测管理主键
     * @return 结果
     */
    public int deleteTDetectById(Long id);

    /**
     * 批量删除检测管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTDetectByIds(Long[] ids);
}
