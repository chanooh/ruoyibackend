package com.ruoyi.city.controller;

// 小程序 工单上传 与 工单列表

import com.ruoyi.city.domain.Reports;
import com.ruoyi.city.service.IReportsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ruoyi.common.core.domain.AjaxResult.success;

@RestController
@RequestMapping("/city/api")
public class MiniReportsController extends BaseController {

    @Autowired
    private IReportsService reportsService;

    @PostMapping("/submit")
    public AjaxResult submit(@RequestBody Reports reports)
    {
        return toAjax(reportsService.insertReports(reports));
    }

    @GetMapping("/list")
    public TableDataInfo list(Reports reports)
    {
        startPage();
        List<Reports> list = reportsService.selectReportsListByUserId(reports);
        return getDataTable(list);
    }


    @PutMapping("/assign")
    public AjaxResult assign(@RequestBody Reports reports)
    {
        return toAjax(reportsService.updateReports(reports));
    }


}
