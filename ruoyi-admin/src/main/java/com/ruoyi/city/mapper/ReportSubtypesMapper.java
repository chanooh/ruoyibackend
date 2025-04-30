package com.ruoyi.city.mapper;

import java.util.List;
import com.ruoyi.city.domain.ReportSubtypes;

/**
 * 工单子类型Mapper接口
 * 
 * @author czl
 * @date 2025-04-29
 */
public interface ReportSubtypesMapper 
{
    /**
     * 查询工单子类型
     * 
     * @param id 工单子类型主键
     * @return 工单子类型
     */
    public ReportSubtypes selectReportSubtypesById(Long id);

    /**
     * 查询工单子类型列表
     * 
     * @param reportSubtypes 工单子类型
     * @return 工单子类型集合
     */
    public List<ReportSubtypes> selectReportSubtypesList(ReportSubtypes reportSubtypes);

    /**
     * 新增工单子类型
     * 
     * @param reportSubtypes 工单子类型
     * @return 结果
     */
    public int insertReportSubtypes(ReportSubtypes reportSubtypes);

    /**
     * 修改工单子类型
     * 
     * @param reportSubtypes 工单子类型
     * @return 结果
     */
    public int updateReportSubtypes(ReportSubtypes reportSubtypes);

    /**
     * 删除工单子类型
     * 
     * @param id 工单子类型主键
     * @return 结果
     */
    public int deleteReportSubtypesById(Long id);

    /**
     * 批量删除工单子类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReportSubtypesByIds(Long[] ids);
}
