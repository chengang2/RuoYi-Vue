package com.ruoyi.suyuan.mapper;

import java.util.List;
import com.ruoyi.suyuan.domain.DeviceInfo;
import com.ruoyi.suyuan.domain.DeviceInfoVO;

/**
 * 设备管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-12
 */
public interface DeviceInfoMapper 
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
     * 删除设备管理
     * 
     * @param id 设备管理主键
     * @return 结果
     */
    public int deleteDeviceInfoById(Integer id);

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeviceInfoByIds(Integer[] ids);
}
