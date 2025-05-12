package com.ruoyi.suyuan.service;

import java.util.List;
import com.ruoyi.suyuan.domain.DeviceInfo;
import com.ruoyi.suyuan.domain.DeviceInfoVO;
import com.ruoyi.suyuan.domain.Enterprise;

/**
 * 设备管理Service接口
 * 
 * @author ruoyi
 * @date 2025-05-12
 */
public interface IDeviceInfoService 
{
    /**
     * 查询设备管理
     * 
     * @param id 设备管理主键
     * @return 设备管理
     */
    public DeviceInfo selectDeviceInfoById(Integer id);

    public DeviceInfo selectDeviceInfoByEnterpriseId(Integer enterpriseId);
    /**
     * 查询设备管理列表
     * 
     * @param deviceInfo 设备管理
     * @return 设备管理集合
     */
    public List<DeviceInfo> selectDeviceInfoList(DeviceInfoVO deviceInfo);

    /**
     * 新增设备管理
     * 
     * @param deviceInfo 设备管理
     * @return 结果
     */
    public int insertDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 修改设备管理
     * 
     * @param deviceInfo 设备管理
     * @return 结果
     */
    public int updateDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的设备管理主键集合
     * @return 结果
     */
    public int deleteDeviceInfoByIds(Integer[] ids);

    /**
     * 删除设备管理信息
     * 
     * @param id 设备管理主键
     * @return 结果
     */
    public int deleteDeviceInfoById(Integer id);
}
