package com.ruoyi.city.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工单图片对象 report_images
 * 
 * @author czl
 * @date 2025-04-22
 */
public class ReportImages extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图片ID */
    private Long id;

    /** 工单ID */
    @Excel(name = "工单ID")
    private Long reportId;

    /** 图片URL */
    @Excel(name = "图片URL")
    private String imageUrl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setReportId(Long reportId) 
    {
        this.reportId = reportId;
    }

    public Long getReportId() 
    {
        return reportId;
    }

    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() 
    {
        return imageUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("reportId", getReportId())
            .append("imageUrl", getImageUrl())
            .append("createTime", getCreateTime())
            .toString();
    }
}
