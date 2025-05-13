package com.ruoyi.city.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 角色对象 roles
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
public class Roles extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    private Long id;

    /** 角色标识（admin/field/role） */
    @Excel(name = "角色标识", readConverterExp = "a=dmin/field/role")
    private String name;

    /** 显示名称（如 管理员/外业人员/某某品质组角色） */
    @Excel(name = "显示名称", readConverterExp = "如=,管=理员/外业人员/某某品质组角色")
    private String displayName;

    /** 状态：0-禁用，1-启用 */
    @Excel(name = "状态：0-禁用，1-启用")
    private Long status;

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

    public void setDisplayName(String displayName) 
    {
        this.displayName = displayName;
    }

    public String getDisplayName() 
    {
        return displayName;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("displayName", getDisplayName())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
