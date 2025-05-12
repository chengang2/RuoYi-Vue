package com.ruoyi.suyuan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.suyuan.domain.DeviceInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.suyuan.mapper.DeviceInfoMapper;
import com.ruoyi.suyuan.domain.DeviceInfo;
import com.ruoyi.suyuan.service.IDeviceInfoService;

/**
 * 设备管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-12
 */
@Service
public class DeviceInfoServiceImpl implements IDeviceInfoService 
{
    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    /**
     * 查询设备管理
     * 
     * @param id 设备管理主键
     * @return 设备管理
     */
    @Override
    public DeviceInfo selectDeviceInfoById(Integer id)
    {
        return deviceInfoMapper.selectDeviceInfoById(id);
    }

    @Override
    public DeviceInfo selectDeviceInfoByEnterpriseId(Integer enterpriseId) {
        return deviceInfoMapper.selectDeviceInfoByEnterpriseId(enterpriseId);
    }

    /**
     * 查询设备管理列表
     * 
     * @param deviceInfo 设备管理
     * @return 设备管理
     */
    @Override
    public List<DeviceInfo> selectDeviceInfoList(DeviceInfoVO deviceInfo)
    {
        return deviceInfoMapper.selectDeviceInfoList(deviceInfo);
    }

    /**
     * 新增设备管理
     * 
     * @param deviceInfo 设备管理
     * @return 结果
     */
    @Override
    public int insertDeviceInfo(DeviceInfo deviceInfo)
    {
        deviceInfo.setCreateTime(DateUtils.getNowDate());
        return deviceInfoMapper.insertDeviceInfo(deviceInfo);
    }

    /**
     * 修改设备管理
     * 
     * @param deviceInfo 设备管理
     * @return 结果
     */
    @Override
    public int updateDeviceInfo(DeviceInfo deviceInfo)
    {
        deviceInfo.setUpdateTime(DateUtils.getNowDate());
        return deviceInfoMapper.updateDeviceInfo(deviceInfo);
    }

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的设备管理主键
     * @return 结果
     */
    @Override
    public int deleteDeviceInfoByIds(Integer[] ids)
    {
        return deviceInfoMapper.deleteDeviceInfoByIds(ids);
    }

    /**
     * 删除设备管理信息
     * 
     * @param id 设备管理主键
     * @return 结果
     */
    @Override
    public int deleteDeviceInfoById(Integer id)
    {
        return deviceInfoMapper.deleteDeviceInfoById(id);
    }
}
