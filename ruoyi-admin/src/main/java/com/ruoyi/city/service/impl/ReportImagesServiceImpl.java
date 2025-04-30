package com.ruoyi.city.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.city.mapper.ReportImagesMapper;
import com.ruoyi.city.domain.ReportImages;
import com.ruoyi.city.service.IReportImagesService;

/**
 * 工单图片Service业务层处理
 * 
 * @author czl
 * @date 2025-04-22
 */
@Service
public class ReportImagesServiceImpl implements IReportImagesService 
{
    @Autowired
    private ReportImagesMapper reportImagesMapper;

    /**
     * 查询工单图片
     * 
     * @param id 工单图片主键
     * @return 工单图片
     */
    @Override
    public ReportImages selectReportImagesById(Long id)
    {
        return reportImagesMapper.selectReportImagesById(id);
    }

    /**
     * 查询工单图片列表
     * 
     * @param reportImages 工单图片
     * @return 工单图片
     */
    @Override
    public List<ReportImages> selectReportImagesList(ReportImages reportImages)
    {
        return reportImagesMapper.selectReportImagesList(reportImages);
    }

    /**
     * 新增工单图片
     * 
     * @param reportImages 工单图片
     * @return 结果
     */
    @Override
    public int insertReportImages(ReportImages reportImages)
    {
        reportImages.setCreateTime(DateUtils.getNowDate());
        return reportImagesMapper.insertReportImages(reportImages);
    }

    /**
     * 修改工单图片
     * 
     * @param reportImages 工单图片
     * @return 结果
     */
    @Override
    public int updateReportImages(ReportImages reportImages)
    {
        return reportImagesMapper.updateReportImages(reportImages);
    }

    /**
     * 批量删除工单图片
     * 
     * @param ids 需要删除的工单图片主键
     * @return 结果
     */
    @Override
    public int deleteReportImagesByIds(Long[] ids)
    {
        return reportImagesMapper.deleteReportImagesByIds(ids);
    }

    /**
     * 删除工单图片信息
     * 
     * @param id 工单图片主键
     * @return 结果
     */
    @Override
    public int deleteReportImagesById(Long id)
    {
        return reportImagesMapper.deleteReportImagesById(id);
    }
}
