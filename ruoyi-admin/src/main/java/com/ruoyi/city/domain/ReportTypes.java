package com.ruoyi.city.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工单类型对象 report_types
 *
 * @author czl
 * @date 2025-04-29
 */
public class ReportTypes extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类型ID */
    private Long id;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String name;

    /** 类型描述 */
    @Excel(name = "类型描述")
    private String description;

    /** 状态：0-禁用, 1-启用 */
    @Excel(name = "状态：0-禁用, 1-启用")
    private Long status;

    /** 工单子类型信息 */
    private List<ReportSubtypes> reportSubtypesList;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }

    public List<ReportSubtypes> getReportSubtypesList()
    {
        return reportSubtypesList;
    }

    public void setReportSubtypesList(List<ReportSubtypes> reportSubtypesList)
    {
        this.reportSubtypesList = reportSubtypesList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("description", getDescription())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("reportSubtypesList", getReportSubtypesList())
                .toString();
    }
}
