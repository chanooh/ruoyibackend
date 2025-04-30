package com.ruoyi.city.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.city.mapper.ReportSubtypesMapper;
import com.ruoyi.city.domain.ReportSubtypes;
import com.ruoyi.city.service.IReportSubtypesService;

/**
 * 工单子类型Service业务层处理
 * 
 * @author czl
 * @date 2025-04-29
 */
@Service
public class ReportSubtypesServiceImpl implements IReportSubtypesService 
{
    @Autowired
    private ReportSubtypesMapper reportSubtypesMapper;

    /**
     * 查询工单子类型
     * 
     * @param id 工单子类型主键
     * @return 工单子类型
     */
    @Override
    public ReportSubtypes selectReportSubtypesById(Long id)
    {
        return reportSubtypesMapper.selectReportSubtypesById(id);
    }

    /**
     * 查询工单子类型列表
     * 
     * @param reportSubtypes 工单子类型
     * @return 工单子类型
     */
    @Override
    public List<ReportSubtypes> selectReportSubtypesList(ReportSubtypes reportSubtypes)
    {
        return reportSubtypesMapper.selectReportSubtypesList(reportSubtypes);
    }

    /**
     * 新增工单子类型
     * 
     * @param reportSubtypes 工单子类型
     * @return 结果
     */
    @Override
    public int insertReportSubtypes(ReportSubtypes reportSubtypes)
    {
        reportSubtypes.setCreateTime(DateUtils.getNowDate());
        return reportSubtypesMapper.insertReportSubtypes(reportSubtypes);
    }

    /**
     * 修改工单子类型
     * 
     * @param reportSubtypes 工单子类型
     * @return 结果
     */
    @Override
    public int updateReportSubtypes(ReportSubtypes reportSubtypes)
    {
        reportSubtypes.setUpdateTime(DateUtils.getNowDate());
        return reportSubtypesMapper.updateReportSubtypes(reportSubtypes);
    }

    /**
     * 批量删除工单子类型
     * 
     * @param ids 需要删除的工单子类型主键
     * @return 结果
     */
    @Override
    public int deleteReportSubtypesByIds(Long[] ids)
    {
        return reportSubtypesMapper.deleteReportSubtypesByIds(ids);
    }

    /**
     * 删除工单子类型信息
     * 
     * @param id 工单子类型主键
     * @return 结果
     */
    @Override
    public int deleteReportSubtypesById(Long id)
    {
        return reportSubtypesMapper.deleteReportSubtypesById(id);
    }
}
