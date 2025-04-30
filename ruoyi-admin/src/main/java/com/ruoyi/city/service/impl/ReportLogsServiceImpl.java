package com.ruoyi.city.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.city.mapper.ReportLogsMapper;
import com.ruoyi.city.domain.ReportLogs;
import com.ruoyi.city.service.IReportLogsService;

/**
 * 工单操作日志Service业务层处理
 * 
 * @author czl
 * @date 2025-04-22
 */
@Service
public class ReportLogsServiceImpl implements IReportLogsService 
{
    @Autowired
    private ReportLogsMapper reportLogsMapper;

    /**
     * 查询工单操作日志
     * 
     * @param id 工单操作日志主键
     * @return 工单操作日志
     */
    @Override
    public ReportLogs selectReportLogsById(Long id)
    {
        return reportLogsMapper.selectReportLogsById(id);
    }

    /**
     * 查询工单操作日志列表
     * 
     * @param reportLogs 工单操作日志
     * @return 工单操作日志
     */
    @Override
    public List<ReportLogs> selectReportLogsList(ReportLogs reportLogs)
    {
        return reportLogsMapper.selectReportLogsList(reportLogs);
    }

    /**
     * 新增工单操作日志
     * 
     * @param reportLogs 工单操作日志
     * @return 结果
     */
    @Override
    public int insertReportLogs(ReportLogs reportLogs)
    {
        reportLogs.setCreateTime(DateUtils.getNowDate());
        return reportLogsMapper.insertReportLogs(reportLogs);
    }

    /**
     * 修改工单操作日志
     * 
     * @param reportLogs 工单操作日志
     * @return 结果
     */
    @Override
    public int updateReportLogs(ReportLogs reportLogs)
    {
        return reportLogsMapper.updateReportLogs(reportLogs);
    }

    /**
     * 批量删除工单操作日志
     * 
     * @param ids 需要删除的工单操作日志主键
     * @return 结果
     */
    @Override
    public int deleteReportLogsByIds(Long[] ids)
    {
        return reportLogsMapper.deleteReportLogsByIds(ids);
    }

    /**
     * 删除工单操作日志信息
     * 
     * @param id 工单操作日志主键
     * @return 结果
     */
    @Override
    public int deleteReportLogsById(Long id)
    {
        return reportLogsMapper.deleteReportLogsById(id);
    }
}
