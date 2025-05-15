package com.ruoyi.suyuan.mapper;

import java.util.List;
import com.ruoyi.suyuan.domain.QrcodeScanLog;
import com.ruoyi.suyuan.domain.ScanLogCName;
import com.ruoyi.suyuan.domain.ScanLogPName;

/**
 * 二维码扫描日志管理Mapper接口
 * 
 * @author chengang
 * @date 2025-05-15
 */
public interface QrcodeScanLogMapper 
{
    /**
     * 查询二维码扫描日志管理
     * 
     * @param id 二维码扫描日志管理主键
     * @return 二维码扫描日志管理
     */
    public QrcodeScanLog selectQrcodeScanLogById(Integer id);

    /**
     * 查询二维码扫描日志管理列表
     * 
     * @param qrcodeScanLog 二维码扫描日志管理
     * @return 二维码扫描日志管理集合
     */
    public List<QrcodeScanLog> selectQrcodeScanLogList(QrcodeScanLog qrcodeScanLog);

    /**
     * 新增二维码扫描日志管理
     * 
     * @param qrcodeScanLog 二维码扫描日志管理
     * @return 结果
     */
    public int insertQrcodeScanLog(QrcodeScanLog qrcodeScanLog);

    /**
     * 修改二维码扫描日志管理
     * 
     * @param qrcodeScanLog 二维码扫描日志管理
     * @return 结果
     */
    public int updateQrcodeScanLog(QrcodeScanLog qrcodeScanLog);

    /**
     * 删除二维码扫描日志管理
     * 
     * @param id 二维码扫描日志管理主键
     * @return 结果
     */
    public int deleteQrcodeScanLogById(Integer id);

    /**
     * 批量删除二维码扫描日志管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQrcodeScanLogByIds(Integer[] ids);

    List<ScanLogCName> selectScanCountByCity();

    int selectScanTotalCount();

    List<ScanLogPName> selectScanCountByProductCategory();
}
