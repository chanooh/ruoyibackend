package com.ruoyi.city.controller;

// 小程序 工单类型与子类型

import com.ruoyi.city.domain.ReportSubtypes;
import com.ruoyi.city.domain.ReportTypes;
import com.ruoyi.city.service.IReportSubtypesService;
import com.ruoyi.city.service.IReportTypesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city/api/types")
public class MiniTypesController extends BaseController {

    @Autowired
    private IReportTypesService reportTypesService;

    @Autowired
    private IReportSubtypesService reportSubtypesService;


    @GetMapping("/list")
    public TableDataInfo list(ReportTypes reportTypes)
    {
        List<ReportTypes> list = reportTypesService.selectReportTypesList(reportTypes);
        return getDataTable(list);
    }


    @GetMapping("/sublist")
    public TableDataInfo list(ReportSubtypes reportSubtypes)
    {
        List<ReportSubtypes> list = reportSubtypesService.selectReportSubtypesList(reportSubtypes);
        return getDataTable(list);
    }



}
