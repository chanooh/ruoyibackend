package com.ruoyi.city.service;

import java.util.List;
import com.ruoyi.city.domain.Reports;

/**
 * 工单管理Service接口
 * 
 * @author czl
 * @date 2025-04-22
 */
public interface IReportsService 
{
    /**
     * 查询工单管理
     * 
     * @param id 工单管理主键
     * @return 工单管理
     */
    public Reports selectReportsById(Long id);

    /**
     * 查询工单管理列表
     * 
     * @param reports 工单管理
     * @return 工单管理集合
     */
    public List<Reports> selectReportsList(Reports reports);

    /**
     * 新增工单管理
     * 
     * @param reports 工单管理
     * @return 结果
     */
    public int insertReports(Reports reports);

    /**
     * 修改工单管理
     * 
     * @param reports 工单管理
     * @return 结果
     */
    public int updateReports(Reports reports);

    /**
     * 批量删除工单管理
     * 
     * @param ids 需要删除的工单管理主键集合
     * @return 结果
     */
    public int deleteReportsByIds(Long[] ids);

    /**
     * 删除工单管理信息
     * 
     * @param id 工单管理主键
     * @return 结果
     */
    public int deleteReportsById(Long id);

    /**
     * 通过用户id 倒序获取工单
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<Reports> selectReportsListByUserId(Long userId);

}
