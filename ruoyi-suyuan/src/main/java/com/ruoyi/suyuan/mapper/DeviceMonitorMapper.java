package com.ruoyi.suyuan.mapper;

import java.util.List;
import com.ruoyi.suyuan.domain.DeviceMonitor;

/**
 * 设备监控管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-12
 */
public interface DeviceMonitorMapper 
{
    /**
     * 查询设备监控管理
     * 
     * @param id 设备监控管理主键
     * @return 设备监控管理
     */
    public DeviceMonitor selectDeviceMonitorById(Long id);

    public DeviceMonitor selectDeviceMonitorByDeviceId(String deviceId);
    /**
     * 查询设备监控管理列表
     * 
     * @param deviceMonitor 设备监控管理
     * @return 设备监控管理集合
     */
    public List<DeviceMonitor> selectDeviceMonitorList(DeviceMonitor deviceMonitor);

    /**
     * 新增设备监控管理
     * 
     * @param deviceMonitor 设备监控管理
     * @return 结果
     */
    public int insertDeviceMonitor(DeviceMonitor deviceMonitor);

    /**
     * 修改设备监控管理
     * 
     * @param deviceMonitor 设备监控管理
     * @return 结果
     */
    public int updateDeviceMonitor(DeviceMonitor deviceMonitor);

    /**
     * 删除设备监控管理
     * 
     * @param id 设备监控管理主键
     * @return 结果
     */
    public int deleteDeviceMonitorById(Long id);

    /**
     * 批量删除设备监控管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeviceMonitorByIds(Long[] ids);
}
