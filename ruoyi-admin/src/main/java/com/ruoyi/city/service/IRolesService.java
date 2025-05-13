package com.ruoyi.city.service;

import java.util.List;
import com.ruoyi.city.domain.Roles;

/**
 * 角色Service接口
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
public interface IRolesService 
{
    /**
     * 查询角色
     * 
     * @param id 角色主键
     * @return 角色
     */
    public Roles selectRolesById(Long id);

    /**
     * 查询角色列表
     * 
     * @param roles 角色
     * @return 角色集合
     */
    public List<Roles> selectRolesList(Roles roles);

    /**
     * 新增角色
     * 
     * @param roles 角色
     * @return 结果
     */
    public int insertRoles(Roles roles);

    /**
     * 修改角色
     * 
     * @param roles 角色
     * @return 结果
     */
    public int updateRoles(Roles roles);

    /**
     * 批量删除角色
     * 
     * @param ids 需要删除的角色主键集合
     * @return 结果
     */
    public int deleteRolesByIds(Long[] ids);

    /**
     * 删除角色信息
     * 
     * @param id 角色主键
     * @return 结果
     */
    public int deleteRolesById(Long id);
}
