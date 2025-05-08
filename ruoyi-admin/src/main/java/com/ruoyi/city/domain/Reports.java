package com.ruoyi.city.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工单管理对象 reports
 * 
 * @author czl
 * @date 2025-04-22
 */
public class Reports extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单ID */
    private Long id;

    /** 提交用户ID */
    @Excel(name = "提交用户ID")
    private Long userId;

    /** 工单类型ID */
    @Excel(name = "工单类型ID")
    private Long typeId;

    /** 工单标题 */
    @Excel(name = "工单标题")
    private String title;

    /** 工单描述 */
    @Excel(name = "工单描述")
    private String description;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 纬度 */
    private BigDecimal latitude;

    /** 经度 */
    private BigDecimal longitude;

    /** 状态：pending-待处理, processing-处理中, completed-已完成, rejected-已拒绝 */
    @Excel(name = "状态：pending-待处理, processing-处理中, completed-已完成, rejected-已拒绝")
    private String status;

    private Long assignedBy;
    private String assignedTo;
    private Date assignedTime;

    /** 工单图片信息 */
    private List<ReportImages> reportImagesList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setLatitude(BigDecimal latitude) 
    {
        this.latitude = latitude;
    }

    public BigDecimal getLatitude() 
    {
        return latitude;
    }

    public void setLongitude(BigDecimal longitude) 
    {
        this.longitude = longitude;
    }

    public BigDecimal getLongitude() 
    {
        return longitude;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public List<ReportImages> getReportImagesList()
    {
        return reportImagesList;
    }

    public void setReportImagesList(List<ReportImages> reportImagesList)
    {
        this.reportImagesList = reportImagesList;
    }

    public Long getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(Long assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Date getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(Date assignedTime) {
        this.assignedTime = assignedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("typeId", getTypeId())
            .append("title", getTitle())
            .append("description", getDescription())
            .append("address", getAddress())
            .append("latitude", getLatitude())
            .append("longitude", getLongitude())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("reportImagesList", getReportImagesList())
            .append("assignedBy",getAssignedBy())
            .append("assignedTo",getAssignedTo())
            .append("assignedTime",getAssignedTime())
            .toString();
    }
}
