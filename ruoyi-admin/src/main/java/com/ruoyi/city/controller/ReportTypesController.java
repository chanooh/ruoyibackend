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
import com.ruoyi.city.domain.ReportTypes;
import com.ruoyi.city.service.IReportTypesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工单类型Controller
 * 
 * @author czl
 * @date 2025-04-22
 */
@RestController
@RequestMapping("/city/types")
public class ReportTypesController extends BaseController
{
    @Autowired
    private IReportTypesService reportTypesService;

    /**
     * 查询工单类型列表
     */
    @PreAuthorize("@ss.hasPermi('city:types:list')")
    @GetMapping("/list")
    public TableDataInfo list(ReportTypes reportTypes)
    {
        startPage();
        List<ReportTypes> list = reportTypesService.selectReportTypesList(reportTypes);
        return getDataTable(list);
    }

    /**
     * 导出工单类型列表
     */
    @PreAuthorize("@ss.hasPermi('city:types:export')")
    @Log(title = "工单类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ReportTypes reportTypes)
    {
        List<ReportTypes> list = reportTypesService.selectReportTypesList(reportTypes);
        ExcelUtil<ReportTypes> util = new ExcelUtil<ReportTypes>(ReportTypes.class);
        util.exportExcel(response, list, "工单类型数据");
    }

    /**
     * 获取工单类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('city:types:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(reportTypesService.selectReportTypesById(id));
    }

    /**
     * 新增工单类型
     */
    @PreAuthorize("@ss.hasPermi('city:types:add')")
    @Log(title = "工单类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReportTypes reportTypes)
    {
        return toAjax(reportTypesService.insertReportTypes(reportTypes));
    }

    /**
     * 修改工单类型
     */
    @PreAuthorize("@ss.hasPermi('city:types:edit')")
    @Log(title = "工单类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReportTypes reportTypes)
    {
        return toAjax(reportTypesService.updateReportTypes(reportTypes));
    }

    /**
     * 删除工单类型
     */
    @PreAuthorize("@ss.hasPermi('city:types:remove')")
    @Log(title = "工单类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(reportTypesService.deleteReportTypesByIds(ids));
    }
}
