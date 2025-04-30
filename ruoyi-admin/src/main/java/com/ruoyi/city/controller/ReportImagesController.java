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
import com.ruoyi.city.domain.ReportImages;
import com.ruoyi.city.service.IReportImagesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工单图片Controller
 * 
 * @author czl
 * @date 2025-04-22
 */
@RestController
@RequestMapping("/city/images")
public class ReportImagesController extends BaseController
{
    @Autowired
    private IReportImagesService reportImagesService;

    /**
     * 查询工单图片列表
     */
    @PreAuthorize("@ss.hasPermi('city:images:list')")
    @GetMapping("/list")
    public TableDataInfo list(ReportImages reportImages)
    {
        startPage();
        List<ReportImages> list = reportImagesService.selectReportImagesList(reportImages);
        return getDataTable(list);
    }

    /**
     * 导出工单图片列表
     */
    @PreAuthorize("@ss.hasPermi('city:images:export')")
    @Log(title = "工单图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ReportImages reportImages)
    {
        List<ReportImages> list = reportImagesService.selectReportImagesList(reportImages);
        ExcelUtil<ReportImages> util = new ExcelUtil<ReportImages>(ReportImages.class);
        util.exportExcel(response, list, "工单图片数据");
    }

    /**
     * 获取工单图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('city:images:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(reportImagesService.selectReportImagesById(id));
    }

    /**
     * 新增工单图片
     */
    @PreAuthorize("@ss.hasPermi('city:images:add')")
    @Log(title = "工单图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReportImages reportImages)
    {
        return toAjax(reportImagesService.insertReportImages(reportImages));
    }

    /**
     * 修改工单图片
     */
    @PreAuthorize("@ss.hasPermi('city:images:edit')")
    @Log(title = "工单图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReportImages reportImages)
    {
        return toAjax(reportImagesService.updateReportImages(reportImages));
    }

    /**
     * 删除工单图片
     */
    @PreAuthorize("@ss.hasPermi('city:images:remove')")
    @Log(title = "工单图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(reportImagesService.deleteReportImagesByIds(ids));
    }
}
