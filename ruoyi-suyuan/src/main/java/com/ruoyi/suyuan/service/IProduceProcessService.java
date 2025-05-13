package com.ruoyi.suyuan.service;

import java.util.List;
import com.ruoyi.suyuan.domain.ProduceProcess;
import com.ruoyi.suyuan.domain.ProduceProcessList;

/**
 * 生产过程管理Service接口
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
public interface IProduceProcessService 
{
    /**
     * 查询生产过程管理
     * 
     * @param id 生产过程管理主键
     * @return 生产过程管理
     */
    public ProduceProcess selectProduceProcessById(Integer id);

    public ProduceProcess selectProduceProcess(ProduceProcess produceProcess);
    /**
     * 查询生产过程管理列表
     * 
     * @param produceProcess 生产过程管理
     * @return 生产过程管理集合
     */
    public List<ProduceProcess> selectProduceProcessList(ProduceProcessList produceProcess);

    /**
     * 新增生产过程管理
     * 
     * @param produceProcess 生产过程管理
     * @return 结果
     */
    public int insertProduceProcess(ProduceProcess produceProcess);

    /**
     * 修改生产过程管理
     * 
     * @param produceProcess 生产过程管理
     * @return 结果
     */
    public int updateProduceProcess(ProduceProcess produceProcess);

    /**
     * 批量删除生产过程管理
     * 
     * @param ids 需要删除的生产过程管理主键集合
     * @return 结果
     */
    public int deleteProduceProcessByIds(Integer[] ids);

    /**
     * 删除生产过程管理信息
     * 
     * @param id 生产过程管理主键
     * @return 结果
     */
    public int deleteProduceProcessById(Integer id);
}
