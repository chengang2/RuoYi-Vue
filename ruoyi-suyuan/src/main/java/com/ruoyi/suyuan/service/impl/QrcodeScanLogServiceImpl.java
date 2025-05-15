package com.ruoyi.suyuan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.suyuan.domain.ScanLogCName;
import com.ruoyi.suyuan.domain.ScanLogPName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.suyuan.mapper.QrcodeScanLogMapper;
import com.ruoyi.suyuan.domain.QrcodeScanLog;
import com.ruoyi.suyuan.service.IQrcodeScanLogService;

/**
 * 二维码扫描日志管理Service业务层处理
 * 
 * @author chengang
 * @date 2025-05-15
 */
@Service
public class QrcodeScanLogServiceImpl implements IQrcodeScanLogService 
{
    @Autowired
    private QrcodeScanLogMapper qrcodeScanLogMapper;

    /**
     * 查询二维码扫描日志管理
     * 
     * @param id 二维码扫描日志管理主键
     * @return 二维码扫描日志管理
     */
    @Override
    public QrcodeScanLog selectQrcodeScanLogById(Integer id)
    {
        return qrcodeScanLogMapper.selectQrcodeScanLogById(id);
    }

    /**
     * 查询二维码扫描日志管理列表
     * 
     * @param qrcodeScanLog 二维码扫描日志管理
     * @return 二维码扫描日志管理
     */
    @Override
    public List<QrcodeScanLog> selectQrcodeScanLogList(QrcodeScanLog qrcodeScanLog)
    {
        return qrcodeScanLogMapper.selectQrcodeScanLogList(qrcodeScanLog);
    }

    /**
     * 新增二维码扫描日志管理
     * 
     * @param qrcodeScanLog 二维码扫描日志管理
     * @return 结果
     */
    @Override
    public int insertQrcodeScanLog(QrcodeScanLog qrcodeScanLog)
    {
        qrcodeScanLog.setCreateTime(DateUtils.getNowDate());
        return qrcodeScanLogMapper.insertQrcodeScanLog(qrcodeScanLog);
    }

    /**
     * 修改二维码扫描日志管理
     * 
     * @param qrcodeScanLog 二维码扫描日志管理
     * @return 结果
     */
    @Override
    public int updateQrcodeScanLog(QrcodeScanLog qrcodeScanLog)
    {
        qrcodeScanLog.setUpdateTime(DateUtils.getNowDate());
        return qrcodeScanLogMapper.updateQrcodeScanLog(qrcodeScanLog);
    }

    /**
     * 批量删除二维码扫描日志管理
     * 
     * @param ids 需要删除的二维码扫描日志管理主键
     * @return 结果
     */
    @Override
    public int deleteQrcodeScanLogByIds(Integer[] ids)
    {
        return qrcodeScanLogMapper.deleteQrcodeScanLogByIds(ids);
    }

    /**
     * 删除二维码扫描日志管理信息
     * 
     * @param id 二维码扫描日志管理主键
     * @return 结果
     */
    @Override
    public int deleteQrcodeScanLogById(Integer id)
    {
        return qrcodeScanLogMapper.deleteQrcodeScanLogById(id);
    }

    @Override
    public List<ScanLogCName> selectScanCountByCity() {
        return qrcodeScanLogMapper.selectScanCountByCity();
    }

    @Override
    public int selectScanTotalCount() {
        return qrcodeScanLogMapper.selectScanTotalCount();
    }

    @Override
    public List<ScanLogPName> selectScanCountByProductCategory() {
        return qrcodeScanLogMapper.selectScanCountByProductCategory();
    }
}
