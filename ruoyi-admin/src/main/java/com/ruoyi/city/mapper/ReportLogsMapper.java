package com.ruoyi.city.mapper;

import java.util.List;
import com.ruoyi.city.domain.ReportLogs;

/**
 * 工单操作日志Mapper接口
 * 
 * @author czl
 * @date 2025-04-22
 */
public interface ReportLogsMapper 
{
    /**
     * 查询工单操作日志
     * 
     * @param id 工单操作日志主键
     * @return 工单操作日志
     */
    public ReportLogs selectReportLogsById(Long id);

    /**
     * 查询工单操作日志列表
     * 
     * @param reportLogs 工单操作日志
     * @return 工单操作日志集合
     */
    public List<ReportLogs> selectReportLogsList(ReportLogs reportLogs);

    /**
     * 新增工单操作日志
     * 
     * @param reportLogs 工单操作日志
     * @return 结果
     */
    public int insertReportLogs(ReportLogs reportLogs);

    /**
     * 修改工单操作日志
     * 
     * @param reportLogs 工单操作日志
     * @return 结果
     */
    public int updateReportLogs(ReportLogs reportLogs);

    /**
     * 删除工单操作日志
     * 
     * @param id 工单操作日志主键
     * @return 结果
     */
    public int deleteReportLogsById(Long id);

    /**
     * 批量删除工单操作日志
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReportLogsByIds(Long[] ids);
}
