package com.ruoyi.suyuan.service;

import java.util.List;
import com.ruoyi.suyuan.domain.TDetect;

/**
 * 检测管理Service接口
 * 
 * @author ruoyi
 * @date 2025-06-12
 */
public interface ITDetectService 
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
     * 批量删除检测管理
     * 
     * @param ids 需要删除的检测管理主键集合
     * @return 结果
     */
    public int deleteTDetectByIds(Long[] ids);

    /**
     * 删除检测管理信息
     * 
     * @param id 检测管理主键
     * @return 结果
     */
    public int deleteTDetectById(Long id);
}
