package com.ruoyi.city.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户管理对象 users
 * 
 * @author czl
 * @date 2025-04-22
 */
public class Users extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 密码 */
    private String password;

    /** 角色 */
    @Excel(name = "角色")
    private String role;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 状态：0-禁用, 1-启用 */
    @Excel(name = "状态：0-禁用, 1-启用")
    private Long status;

    private String roleDisplayName;

    public void setRoleDisplayName(String roleDisplayName) {
        this.roleDisplayName = roleDisplayName;
    }
    public String getRoleDisplayName() {
        return this.roleDisplayName;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setRole(String role) 
    {
        this.role = role;
    }

    public String getRole() 
    {
        return role;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
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
            .append("username", getUsername())
            .append("password", getPassword())
            .append("role", getRole())
            .append("phone", getPhone())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
