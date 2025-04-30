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
import com.ruoyi.city.domain.ReportSubtypes;
import com.ruoyi.city.service.IReportSubtypesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工单子类型Controller
 * 
 * @author czl
 * @date 2025-04-29
 */
@RestController
@RequestMapping("/city/subtypes")
public class ReportSubtypesController extends BaseController
{
    @Autowired
    private IReportSubtypesService reportSubtypesService;

    /**
     * 查询工单子类型列表
     */
    @PreAuthorize("@ss.hasPermi('city:subtypes:list')")
    @GetMapping("/list")
    public TableDataInfo list(ReportSubtypes reportSubtypes)
    {
        startPage();
        List<ReportSubtypes> list = reportSubtypesService.selectReportSubtypesList(reportSubtypes);
        return getDataTable(list);
    }

    /**
     * 导出工单子类型列表
     */
    @PreAuthorize("@ss.hasPermi('city:subtypes:export')")
    @Log(title = "工单子类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ReportSubtypes reportSubtypes)
    {
        List<ReportSubtypes> list = reportSubtypesService.selectReportSubtypesList(reportSubtypes);
        ExcelUtil<ReportSubtypes> util = new ExcelUtil<ReportSubtypes>(ReportSubtypes.class);
        util.exportExcel(response, list, "工单子类型数据");
    }

    /**
     * 获取工单子类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('city:subtypes:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(reportSubtypesService.selectReportSubtypesById(id));
    }

    /**
     * 新增工单子类型
     */
    @PreAuthorize("@ss.hasPermi('city:subtypes:add')")
    @Log(title = "工单子类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReportSubtypes reportSubtypes)
    {
        return toAjax(reportSubtypesService.insertReportSubtypes(reportSubtypes));
    }

    /**
     * 修改工单子类型
     */
    @PreAuthorize("@ss.hasPermi('city:subtypes:edit')")
    @Log(title = "工单子类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReportSubtypes reportSubtypes)
    {
        return toAjax(reportSubtypesService.updateReportSubtypes(reportSubtypes));
    }

    /**
     * 删除工单子类型
     */
    @PreAuthorize("@ss.hasPermi('city:subtypes:remove')")
    @Log(title = "工单子类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(reportSubtypesService.deleteReportSubtypesByIds(ids));
    }
}
