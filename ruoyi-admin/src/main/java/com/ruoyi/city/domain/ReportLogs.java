package com.ruoyi.city.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工单操作日志对象 report_logs
 * 
 * @author czl
 * @date 2025-04-22
 */
public class ReportLogs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long id;

    /** 工单ID */
    @Excel(name = "工单ID")
    private Long reportId;

    /** 操作用户ID */
    @Excel(name = "操作用户ID")
    private Long userId;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private String action;

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

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setAction(String action) 
    {
        this.action = action;
    }

    public String getAction() 
    {
        return action;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("reportId", getReportId())
            .append("userId", getUserId())
            .append("action", getAction())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .toString();
    }
}
