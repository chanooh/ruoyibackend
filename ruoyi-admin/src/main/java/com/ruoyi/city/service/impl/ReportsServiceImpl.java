package com.ruoyi.city.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.city.domain.ReportImages;
import com.ruoyi.city.mapper.ReportsMapper;
import com.ruoyi.city.domain.Reports;
import com.ruoyi.city.service.IReportsService;

/**
 * 工单管理Service业务层处理
 * 
 * @author czl
 * @date 2025-04-22
 */
@Service
public class ReportsServiceImpl implements IReportsService 
{
    @Autowired
    private ReportsMapper reportsMapper;

    /**
     * 查询工单管理
     * 
     * @param id 工单管理主键
     * @return 工单管理
     */
    @Override
    public Reports selectReportsById(Long id)
    {
        return reportsMapper.selectReportsById(id);
    }

    /**
     * 查询工单管理列表
     * 
     * @param reports 工单管理
     * @return 工单管理
     */
    @Override
    public List<Reports> selectReportsList(Reports reports)
    {
        return reportsMapper.selectReportsList(reports);
    }

    /**
     * 新增工单管理
     * 
     * @param reports 工单管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertReports(Reports reports)
    {
        reports.setCreateTime(DateUtils.getNowDate());
        int rows = reportsMapper.insertReports(reports);
        insertReportImages(reports);
        return rows;
    }

    /**
     * 修改工单管理
     * 
     * @param reports 工单管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateReports(Reports reports)
    {
        reports.setUpdateTime(DateUtils.getNowDate());
        reportsMapper.deleteReportImagesByReportId(reports.getId());
        insertReportImages(reports);
        return reportsMapper.updateReports(reports);
    }

    /**
     * 批量删除工单管理
     * 
     * @param ids 需要删除的工单管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteReportsByIds(Long[] ids)
    {
        reportsMapper.deleteReportImagesByReportIds(ids);
        return reportsMapper.deleteReportsByIds(ids);
    }

    /**
     * 删除工单管理信息
     * 
     * @param id 工单管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteReportsById(Long id)
    {
        reportsMapper.deleteReportImagesByReportId(id);
        return reportsMapper.deleteReportsById(id);
    }

    /**
     * 新增工单图片信息
     * 
     * @param reports 工单管理对象
     */
    public void insertReportImages(Reports reports)
    {
        List<ReportImages> reportImagesList = reports.getReportImagesList();
        Long id = reports.getId();
        if (StringUtils.isNotNull(reportImagesList))
        {
            List<ReportImages> list = new ArrayList<ReportImages>();
            for (ReportImages reportImages : reportImagesList)
            {
                reportImages.setReportId(id);
                list.add(reportImages);
            }
            if (list.size() > 0)
            {
                reportsMapper.batchReportImages(list);
            }
        }
    }

    @Override
    public List<Reports> selectReportsListByUserId(Reports reports)
    {
        return reportsMapper.selectReportsListByUserId(reports);
    }
}
