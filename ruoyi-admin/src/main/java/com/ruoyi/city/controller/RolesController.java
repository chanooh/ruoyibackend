package com.ruoyi.city.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.city.domain.Roles;
import com.ruoyi.city.service.IRolesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 角色Controller
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
@RestController
@RequestMapping("/city/roles")
public class RolesController extends BaseController
{
    @Autowired
    private IRolesService rolesService;

    /**
     * 查询角色列表
     */
    @PreAuthorize("@ss.hasPermi('city:roles:list')")
    @GetMapping("/list")
    public TableDataInfo list(Roles roles)
    {
        startPage();
        List<Roles> list = rolesService.selectRolesList(roles);
        return getDataTable(list);
    }

    /**
     * 导出角色列表
     */
    @PreAuthorize("@ss.hasPermi('city:roles:export')")
    @Log(title = "角色", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Roles roles)
    {
        List<Roles> list = rolesService.selectRolesList(roles);
        ExcelUtil<Roles> util = new ExcelUtil<Roles>(Roles.class);
        util.exportExcel(response, list, "角色数据");
    }

    /**
     * 获取角色详细信息
     */
    @PreAuthorize("@ss.hasPermi('city:roles:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(rolesService.selectRolesById(id));
    }

    /**
     * 新增角色
     */
    @PreAuthorize("@ss.hasPermi('city:roles:add')")
    @Log(title = "角色", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Roles roles)
    {
        return toAjax(rolesService.insertRoles(roles));
    }

    /**
     * 修改角色
     */
    @PreAuthorize("@ss.hasPermi('city:roles:edit')")
    @Log(title = "角色", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Roles roles)
    {
        return toAjax(rolesService.updateRoles(roles));
    }

    /**
     * 删除角色
     */
    @PreAuthorize("@ss.hasPermi('city:roles:remove')")
    @Log(title = "角色", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rolesService.deleteRolesByIds(ids));
    }
}
