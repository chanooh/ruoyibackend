package com.ruoyi.city.mapper;

import java.util.List;
import com.ruoyi.city.domain.ReportTypes;
import com.ruoyi.city.domain.ReportSubtypes;

/**
 * 工单类型Mapper接口
 *
 * @author czl
 * @date 2025-04-29
 */
public interface ReportTypesMapper
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
     * 删除工单类型
     *
     * @param id 工单类型主键
     * @return 结果
     */
    public int deleteReportTypesById(Long id);

    /**
     * 批量删除工单类型
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReportTypesByIds(Long[] ids);

    /**
     * 批量删除工单子类型
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReportSubtypesByTypeIds(Long[] ids);

    /**
     * 批量新增工单子类型
     *
     * @param reportSubtypesList 工单子类型列表
     * @return 结果
     */
    public int batchReportSubtypes(List<ReportSubtypes> reportSubtypesList);


    /**
     * 通过工单类型主键删除工单子类型信息
     *
     * @param id 工单类型ID
     * @return 结果
     */
    public int deleteReportSubtypesByTypeId(Long id);
}
