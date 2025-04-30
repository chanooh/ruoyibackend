package com.ruoyi.city.service;

import java.util.List;
import com.ruoyi.city.domain.ReportTypes;

/**
 * 工单类型Service接口
 *
 * @author czl
 * @date 2025-04-29
 */
public interface IReportTypesService
{
    /**
     * 查询工单类型
     *
     * @param id 工单类型主键
     * @return 工单类型
     */
    public ReportTypes selectReportTypesById(Long id);

    /**
     * 查询工单类型列表
     *
     * @param reportTypes 工单类型
     * @return 工单类型集合
     */
    public List<ReportTypes> selectReportTypesList(ReportTypes reportTypes);

    /**
     * 新增工单类型
     *
     * @param reportTypes 工单类型
     * @return 结果
     */
    public int insertReportTypes(ReportTypes reportTypes);

    /**
     * 修改工单类型
     *
     * @param reportTypes 工单类型
     * @return 结果
     */
    public int updateReportTypes(ReportTypes reportTypes);

    /**
     * 批量删除工单类型
     *
     * @param ids 需要删除的工单类型主键集合
     * @return 结果
     */
    public int deleteReportTypesByIds(Long[] ids);

    /**
     * 删除工单类型信息
     *
     * @param id 工单类型主键
     * @return 结果
     */
    public int deleteReportTypesById(Long id);
}
