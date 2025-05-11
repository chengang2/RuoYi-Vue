package com.ruoyi.suyuan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.suyuan.mapper.EnterpriseMapper;
import com.ruoyi.suyuan.domain.Enterprise;
import com.ruoyi.suyuan.service.IEnterpriseService;

/**
 * 企业管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-10
 */
@Service
public class EnterpriseServiceImpl implements IEnterpriseService 
{
    @Autowired
    private EnterpriseMapper enterpriseMapper;

    /**
     * 查询企业管理
     * 
     * @param id 企业管理主键
     * @return 企业管理
     */
    @Override
    public Enterprise selectEnterpriseById(Integer id)
    {
        return enterpriseMapper.selectEnterpriseById(id);
    }

    @Override
    public Enterprise selectEnterpriseByName(String name) {
        return enterpriseMapper.selectEnterpriseByName(name);
    }

    @Override
    public List<Enterprise> selectEnterpriseByParentId(Integer id) {
        return enterpriseMapper.selectEnterpriseByParentId(id);
    }

    @Override
    public List<Enterprise> selectEnterpriseByParentAndSelfId(Integer id) {
        return enterpriseMapper.selectEnterpriseByParentAndSelfId(id);
    }

    @Override
    public List<Enterprise> selectEnterpriseByZero() {
        return enterpriseMapper.selectEnterpriseByZero();
    }

    /**
     * 查询企业管理列表
     * 
     * @param enterprise 企业管理
     * @return 企业管理
     */
    @Override
    public List<Enterprise> selectEnterpriseList(Enterprise enterprise)
    {
        return enterpriseMapper.selectEnterpriseList(enterprise);
    }

    /**
     * 新增企业管理
     * 
     * @param enterprise 企业管理
     * @return 结果
     */
    @Override
    public int insertEnterprise(Enterprise enterprise)
    {
        enterprise.setCreateTime(DateUtils.getNowDate());
        return enterpriseMapper.insertEnterprise(enterprise);
    }

    /**
     * 修改企业管理
     * 
     * @param enterprise 企业管理
     * @return 结果
     */
    @Override
    public int updateEnterprise(Enterprise enterprise)
    {
        enterprise.setUpdateTime(DateUtils.getNowDate());
        return enterpriseMapper.updateEnterprise(enterprise);
    }

    /**
     * 批量删除企业管理
     * 
     * @param ids 需要删除的企业管理主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseByIds(Integer[] ids)
    {
        return enterpriseMapper.deleteEnterpriseByIds(ids);
    }

    /**
     * 删除企业管理信息
     * 
     * @param id 企业管理主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseById(Integer id)
    {
        return enterpriseMapper.deleteEnterpriseById(id);
    }
}
