package com.ruoyi.city.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.city.service.IReportImagesService;
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
import com.ruoyi.city.domain.Reports;
import com.ruoyi.city.service.IReportsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工单管理Controller
 * 
 * @author czl
 * @date 2025-04-22
 */
@RestController
@RequestMapping("/city/reports")
public class ReportsController extends BaseController
{
    @Autowired
    private IReportsService reportsService;
    /**
     * 查询工单管理列表
     */
    @PreAuthorize("@ss.hasPermi('city:reports:list')")
    @GetMapping("/list")
    public TableDataInfo list(Reports reports)
    {
        startPage();
        List<Reports> list = reportsService.selectReportsList(reports);
        return getDataTable(list);
    }

    /**
     * 导出工单管理列表
     */
    @PreAuthorize("@ss.hasPermi('city:reports:export')")
    @Log(title = "工单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Reports reports)
    {
        List<Reports> list = reportsService.selectReportsList(reports);
        ExcelUtil<Reports> util = new ExcelUtil<Reports>(Reports.class);
        util.exportExcel(response, list, "工单管理数据");
    }

    /**
     * 获取工单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('city:reports:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(reportsService.selectReportsById(id));
    }

    /**
     * 新增工单管理
     */
    @PreAuthorize("@ss.hasPermi('city:reports:add')")
    @Log(title = "工单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Reports reports)
    {
        return toAjax(reportsService.insertReports(reports));
    }

    /**
     * 修改工单管理
     */
    @PreAuthorize("@ss.hasPermi('city:reports:edit')")
    @Log(title = "工单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Reports reports)
    {
        return toAjax(reportsService.updateReports(reports));
    }

    /**
     * 删除工单管理
     */
    @PreAuthorize("@ss.hasPermi('city:reports:remove')")
    @Log(title = "工单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(reportsService.deleteReportsByIds(ids));
    }
}
