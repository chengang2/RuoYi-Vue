package com.ruoyi.suyuan.service;

import java.util.List;
import com.ruoyi.suyuan.domain.DeviceVoice;
import com.ruoyi.suyuan.domain.DeviceVoiceVO;

/**
 * 视频设备管理Service接口
 * 
 * @author ruoyi
 * @date 2025-05-12
 */
public interface IDeviceVoiceService 
{
    /**
     * 查询视频设备管理
     * 
     * @param id 视频设备管理主键
     * @return 视频设备管理
     */
    public DeviceVoice selectDeviceVoiceById(Long id);

    /**
     * 查询视频设备管理列表
     * 
     * @param deviceVoice 视频设备管理
     * @return 视频设备管理集合
     */
    public List<DeviceVoice> selectDeviceVoiceList(DeviceVoiceVO deviceVoice);

    /**
     * 新增视频设备管理
     * 
     * @param deviceVoice 视频设备管理
     * @return 结果
     */
    public int insertDeviceVoice(DeviceVoice deviceVoice);

    /**
     * 修改视频设备管理
     * 
     * @param deviceVoice 视频设备管理
     * @return 结果
     */
    public int updateDeviceVoice(DeviceVoice deviceVoice);

    /**
     * 批量删除视频设备管理
     * 
     * @param ids 需要删除的视频设备管理主键集合
     * @return 结果
     */
    public int deleteDeviceVoiceByIds(Long[] ids);

    /**
     * 删除视频设备管理信息
     * 
     * @param id 视频设备管理主键
     * @return 结果
     */
    public int deleteDeviceVoiceById(Long id);
}
