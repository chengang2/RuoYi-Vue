package com.ruoyi.suyuan.service;

import com.ruoyi.suyuan.domain.Certification;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
public interface ICertificationService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Certification selectCertificationById(String id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param certification 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Certification> selectCertificationList(Certification certification);

    /**
     * 新增【请填写功能名称】
     * 
     * @param certification 【请填写功能名称】
     * @return 结果
     */
    public int insertCertification(Certification certification);

    /**
     * 修改【请填写功能名称】
     * 
     * @param certification 【请填写功能名称】
     * @return 结果
     */
    public int updateCertification(Certification certification);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteCertificationByIds(String[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCertificationById(String id);
}
