package com.ruoyi.suyuan.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.suyuan.domain.Certification;
import com.ruoyi.suyuan.mapper.CertificationMapper;
import com.ruoyi.suyuan.service.ICertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
@Service
public class CertificationServiceImpl implements ICertificationService
{
    @Autowired
    private CertificationMapper certificationMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Certification selectCertificationById(String id)
    {
        return certificationMapper.selectCertificationById(id);
    }

    @Override
    public Certification selectCertification(Certification certification) {

        return certificationMapper.selectCertification(certification);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param certification 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Certification> selectCertificationList(Certification certification)
    {
        return certificationMapper.selectCertificationList(certification);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param certification 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCertification(Certification certification)
    {
        certification.setCreateTime(DateUtils.getNowDate());
        return certificationMapper.insertCertification(certification);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param certification 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCertification(Certification certification)
    {
        certification.setUpdateTime(DateUtils.getNowDate());
        return certificationMapper.updateCertification(certification);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCertificationByIds(Long[] ids)
    {
        return certificationMapper.deleteCertificationByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCertificationById(Long id)
    {
        return certificationMapper.deleteCertificationById(id);
    }
}
