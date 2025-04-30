package com.ruoyi.city.mapper;

import java.util.List;
import com.ruoyi.city.domain.ReportImages;

/**
 * 工单图片Mapper接口
 * 
 * @author czl
 * @date 2025-04-22
 */
public interface ReportImagesMapper 
{
    /**
     * 查询工单图片
     * 
     * @param id 工单图片主键
     * @return 工单图片
     */
    public ReportImages selectReportImagesById(Long id);

    /**
     * 查询工单图片列表
     * 
     * @param reportImages 工单图片
     * @return 工单图片集合
     */
    public List<ReportImages> selectReportImagesList(ReportImages reportImages);

    /**
     * 新增工单图片
     * 
     * @param reportImages 工单图片
     * @return 结果
     */
    public int insertReportImages(ReportImages reportImages);

    /**
     * 修改工单图片
     * 
     * @param reportImages 工单图片
     * @return 结果
     */
    public int updateReportImages(ReportImages reportImages);

    /**
     * 删除工单图片
     * 
     * @param id 工单图片主键
     * @return 结果
     */
    public int deleteReportImagesById(Long id);

    /**
     * 批量删除工单图片
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReportImagesByIds(Long[] ids);
}
