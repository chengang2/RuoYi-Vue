package com.ruoyi.suyuan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.suyuan.mapper.DeviceMonitorMapper;
import com.ruoyi.suyuan.domain.DeviceMonitor;
import com.ruoyi.suyuan.service.IDeviceMonitorService;

/**
 * 设备监控管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-12
 */
@Service
public class DeviceMonitorServiceImpl implements IDeviceMonitorService 
{
    @Autowired
    private DeviceMonitorMapper deviceMonitorMapper;

    /**
     * 查询设备监控管理
     * 
     * @param id 设备监控管理主键
     * @return 设备监控管理
     */
    @Override
    public DeviceMonitor selectDeviceMonitorById(Long id)
    {
        return deviceMonitorMapper.selectDeviceMonitorById(id);
    }

    @Override
    public DeviceMonitor selectDeviceMonitorByDeviceId(String deviceId) {
        return deviceMonitorMapper.selectDeviceMonitorByDeviceId(deviceId);
    }

    /**
     * 查询设备监控管理列表
     * 
     * @param deviceMonitor 设备监控管理
     * @return 设备监控管理
     */
    @Override
    public List<DeviceMonitor> selectDeviceMonitorList(DeviceMonitor deviceMonitor)
    {
        return deviceMonitorMapper.selectDeviceMonitorList(deviceMonitor);
    }

    /**
     * 新增设备监控管理
     * 
     * @param deviceMonitor 设备监控管理
     * @return 结果
     */
    @Override
    public int insertDeviceMonitor(DeviceMonitor deviceMonitor)
    {
        deviceMonitor.setCreateTime(DateUtils.getNowDate());
        return deviceMonitorMapper.insertDeviceMonitor(deviceMonitor);
    }

    /**
     * 修改设备监控管理
     * 
     * @param deviceMonitor 设备监控管理
     * @return 结果
     */
    @Override
    public int updateDeviceMonitor(DeviceMonitor deviceMonitor)
    {
        deviceMonitor.setUpdateTime(DateUtils.getNowDate());
        return deviceMonitorMapper.updateDeviceMonitor(deviceMonitor);
    }

    /**
     * 批量删除设备监控管理
     * 
     * @param ids 需要删除的设备监控管理主键
     * @return 结果
     */
    @Override
    public int deleteDeviceMonitorByIds(Long[] ids)
    {
        return deviceMonitorMapper.deleteDeviceMonitorByIds(ids);
    }

    /**
     * 删除设备监控管理信息
     * 
     * @param id 设备监控管理主键
     * @return 结果
     */
    @Override
    public int deleteDeviceMonitorById(Long id)
    {
        return deviceMonitorMapper.deleteDeviceMonitorById(id);
    }
}
