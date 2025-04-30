package com.ruoyi.city.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.city.domain.ReportLogs;
import com.ruoyi.city.service.IReportLogsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工单操作日志Controller
 * 
 * @author czl
 * @date 2025-04-22
 */
@RestController
@RequestMapping("/city/logs")
public class ReportLogsController extends BaseController
{
    @Autowired
    private IReportLogsService reportLogsService;

    /**
     * 查询工单操作日志列表
     */
    @PreAuthorize("@ss.hasPermi('city:logs:list')")
    @GetMapping("/list")
    public TableDataInfo list(ReportLogs reportLogs)
    {
        startPage();
        List<ReportLogs> list = reportLogsService.selectReportLogsList(reportLogs);
        return getDataTable(list);
    }

    /**
     * 导出工单操作日志列表
     */
    @PreAuthorize("@ss.hasPermi('city:logs:export')")
    @Log(title = "工单操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ReportLogs reportLogs)
    {
        List<ReportLogs> list = reportLogsService.selectReportLogsList(reportLogs);
        ExcelUtil<ReportLogs> util = new ExcelUtil<ReportLogs>(ReportLogs.class);
        util.exportExcel(response, list, "工单操作日志数据");
    }

    /**
     * 获取工单操作日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('city:logs:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(reportLogsService.selectReportLogsById(id));
    }

    /**
     * 新增工单操作日志
     */
    @PreAuthorize("@ss.hasPermi('city:logs:add')")
    @Log(title = "工单操作日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReportLogs reportLogs)
    {
        return toAjax(reportLogsService.insertReportLogs(reportLogs));
    }

    /**
     * 修改工单操作日志
     */
    @PreAuthorize("@ss.hasPermi('city:logs:edit')")
    @Log(title = "工单操作日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReportLogs reportLogs)
    {
        return toAjax(reportLogsService.updateReportLogs(reportLogs));
    }

    /**
     * 删除工单操作日志
     */
    @PreAuthorize("@ss.hasPermi('city:logs:remove')")
    @Log(title = "工单操作日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(reportLogsService.deleteReportLogsByIds(ids));
    }
}
