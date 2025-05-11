package com.ruoyi.suyuan.mapper;

import java.util.List;
import com.ruoyi.suyuan.domain.Enterprise;

/**
 * 企业管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-10
 */
public interface EnterpriseMapper 
{
    /**
     * 查询企业管理
     * 
     * @param id 企业管理主键
     * @return 企业管理
     */
    public Enterprise selectEnterpriseById(Integer id);

    public List<Enterprise> selectEnterpriseByParentId(Integer id);

    public List<Enterprise> selectEnterpriseByParentAndSelfId(Integer id);

    public List<Enterprise> selectEnterpriseByZero();
    /**
     * 查询企业管理
     *
     * @param name 企业名称
     * @return 企业管理
     */
    public Enterprise selectEnterpriseByName(String name);

    /**
     * 查询企业管理列表
     * 
     * @param enterprise 企业管理
     * @return 企业管理集合
     */
    public List<Enterprise> selectEnterpriseList(Enterprise enterprise);

    /**
     * 新增企业管理
     * 
     * @param enterprise 企业管理
     * @return 结果
     */
    public int insertEnterprise(Enterprise enterprise);

    /**
     * 修改企业管理
     * 
     * @param enterprise 企业管理
     * @return 结果
     */
    public int updateEnterprise(Enterprise enterprise);

    /**
     * 删除企业管理
     * 
     * @param id 企业管理主键
     * @return 结果
     */
    public int deleteEnterpriseById(Integer id);

    /**
     * 批量删除企业管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseByIds(Integer[] ids);
}
