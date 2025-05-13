package com.ruoyi.city.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.city.mapper.RolesMapper;
import com.ruoyi.city.domain.Roles;
import com.ruoyi.city.service.IRolesService;

/**
 * 角色Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
@Service
public class RolesServiceImpl implements IRolesService 
{
    @Autowired
    private RolesMapper rolesMapper;

    /**
     * 查询角色
     * 
     * @param id 角色主键
     * @return 角色
     */
    @Override
    public Roles selectRolesById(Long id)
    {
        return rolesMapper.selectRolesById(id);
    }

    /**
     * 查询角色列表
     * 
     * @param roles 角色
     * @return 角色
     */
    @Override
    public List<Roles> selectRolesList(Roles roles)
    {
        return rolesMapper.selectRolesList(roles);
    }

    /**
     * 新增角色
     * 
     * @param roles 角色
     * @return 结果
     */
    @Override
    public int insertRoles(Roles roles)
    {
        roles.setCreateTime(DateUtils.getNowDate());
        return rolesMapper.insertRoles(roles);
    }

    /**
     * 修改角色
     * 
     * @param roles 角色
     * @return 结果
     */
    @Override
    public int updateRoles(Roles roles)
    {
        roles.setUpdateTime(DateUtils.getNowDate());
        return rolesMapper.updateRoles(roles);
    }

    /**
     * 批量删除角色
     * 
     * @param ids 需要删除的角色主键
     * @return 结果
     */
    @Override
    public int deleteRolesByIds(Long[] ids)
    {
        return rolesMapper.deleteRolesByIds(ids);
    }

    /**
     * 删除角色信息
     * 
     * @param id 角色主键
     * @return 结果
     */
    @Override
    public int deleteRolesById(Long id)
    {
        return rolesMapper.deleteRolesById(id);
    }
}
