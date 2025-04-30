package com.ruoyi.city.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.city.domain.ReportSubtypes;
import com.ruoyi.city.mapper.ReportTypesMapper;
import com.ruoyi.city.domain.ReportTypes;
import com.ruoyi.city.service.IReportTypesService;

/**
 * 工单类型Service业务层处理
 *
 * @author czl
 * @date 2025-04-29
 */
@Service
public class ReportTypesServiceImpl implements IReportTypesService
{
    @Autowired
    private ReportTypesMapper reportTypesMapper;

    /**
     * 查询工单类型
     *
     * @param id 工单类型主键
     * @return 工单类型
     */
    @Override
    public ReportTypes selectReportTypesById(Long id)
    {
        return reportTypesMapper.selectReportTypesById(id);
    }

    /**
     * 查询工单类型列表
     *
     * @param reportTypes 工单类型
     * @return 工单类型
     */
    @Override
    public List<ReportTypes> selectReportTypesList(ReportTypes reportTypes)
    {
        return reportTypesMapper.selectReportTypesList(reportTypes);
    }

    /**
     * 新增工单类型
     *
     * @param reportTypes 工单类型
     * @return 结果
     */
    @Transactional
    @Override
    public int insertReportTypes(ReportTypes reportTypes)
    {
        reportTypes.setCreateTime(DateUtils.getNowDate());
        int rows = reportTypesMapper.insertReportTypes(reportTypes);
        insertReportSubtypes(reportTypes);
        return rows;
    }

    /**
     * 修改工单类型
     *
     * @param reportTypes 工单类型
     * @return 结果
     */
    @Transactional
    @Override
    public int updateReportTypes(ReportTypes reportTypes)
    {
        reportTypes.setUpdateTime(DateUtils.getNowDate());
        reportTypesMapper.deleteReportSubtypesByTypeId(reportTypes.getId());
        insertReportSubtypes(reportTypes);
        return reportTypesMapper.updateReportTypes(reportTypes);
    }

    /**
     * 批量删除工单类型
     *
     * @param ids 需要删除的工单类型主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteReportTypesByIds(Long[] ids)
    {
        reportTypesMapper.deleteReportSubtypesByTypeIds(ids);
        return reportTypesMapper.deleteReportTypesByIds(ids);
    }

    /**
     * 删除工单类型信息
     *
     * @param id 工单类型主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteReportTypesById(Long id)
    {
        reportTypesMapper.deleteReportSubtypesByTypeId(id);
        return reportTypesMapper.deleteReportTypesById(id);
    }

    /**
     * 新增工单子类型信息
     *
     * @param reportTypes 工单类型对象
     */
    public void insertReportSubtypes(ReportTypes reportTypes)
    {
        List<ReportSubtypes> reportSubtypesList = reportTypes.getReportSubtypesList();
        Long id = reportTypes.getId();
        if (StringUtils.isNotNull(reportSubtypesList))
        {
            List<ReportSubtypes> list = new ArrayList<ReportSubtypes>();
            for (ReportSubtypes reportSubtypes : reportSubtypesList)
            {
                reportSubtypes.setTypeId(id);
                list.add(reportSubtypes);
            }
            if (list.size() > 0)
            {
                reportTypesMapper.batchReportSubtypes(list);
            }
        }
    }
}
