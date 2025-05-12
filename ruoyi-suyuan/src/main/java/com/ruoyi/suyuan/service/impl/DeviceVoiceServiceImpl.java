package com.ruoyi.suyuan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.suyuan.domain.DeviceVoiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.suyuan.mapper.DeviceVoiceMapper;
import com.ruoyi.suyuan.domain.DeviceVoice;
import com.ruoyi.suyuan.service.IDeviceVoiceService;

/**
 * 视频设备管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-12
 */
@Service
public class DeviceVoiceServiceImpl implements IDeviceVoiceService 
{
    @Autowired
    private DeviceVoiceMapper deviceVoiceMapper;

    /**
     * 查询视频设备管理
     * 
     * @param id 视频设备管理主键
     * @return 视频设备管理
     */
    @Override
    public DeviceVoice selectDeviceVoiceById(Long id)
    {
        return deviceVoiceMapper.selectDeviceVoiceById(id);
    }

    /**
     * 查询视频设备管理列表
     * 
     * @param deviceVoice 视频设备管理
     * @return 视频设备管理
     */
    @Override
    public List<DeviceVoice> selectDeviceVoiceList(DeviceVoiceVO deviceVoice)
    {
        return deviceVoiceMapper.selectDeviceVoiceList(deviceVoice);
    }

    /**
     * 新增视频设备管理
     * 
     * @param deviceVoice 视频设备管理
     * @return 结果
     */
    @Override
    public int insertDeviceVoice(DeviceVoice deviceVoice)
    {
        deviceVoice.setCreateTime(DateUtils.getNowDate());
        return deviceVoiceMapper.insertDeviceVoice(deviceVoice);
    }

    /**
     * 修改视频设备管理
     * 
     * @param deviceVoice 视频设备管理
     * @return 结果
     */
    @Override
    public int updateDeviceVoice(DeviceVoice deviceVoice)
    {
        deviceVoice.setUpdateTime(DateUtils.getNowDate());
        return deviceVoiceMapper.updateDeviceVoice(deviceVoice);
    }

    /**
     * 批量删除视频设备管理
     * 
     * @param ids 需要删除的视频设备管理主键
     * @return 结果
     */
    @Override
    public int deleteDeviceVoiceByIds(Long[] ids)
    {
        return deviceVoiceMapper.deleteDeviceVoiceByIds(ids);
    }

    /**
     * 删除视频设备管理信息
     * 
     * @param id 视频设备管理主键
     * @return 结果
     */
    @Override
    public int deleteDeviceVoiceById(Long id)
    {
        return deviceVoiceMapper.deleteDeviceVoiceById(id);
    }
}
