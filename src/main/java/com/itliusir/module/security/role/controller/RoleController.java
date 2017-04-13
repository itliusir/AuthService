package com.itliusir.module.security.role.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itliusir.module.security.permission.entity.Permission;
import com.itliusir.module.security.permission.service.PermissionService;
import com.itliusir.module.security.role.entity.Role;
import com.itliusir.module.security.role.service.RoleService;

import javax.servlet.http.HttpServletResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by itliusir
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController{

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 角色列表
     * @return
     */
    @GetMapping("/list")
    public List<Role> list() {
    	List<Role> list = roleService.findAll();
        return list;
    }

    /**
     * 保存配置的权限
     *
     * @param permissionIds
     * @param name
     * @param description
     * @return
     */
    @PostMapping("/add")
    public List<Role> save(@RequestHeader String name,@RequestHeader String description,@RequestBody Integer[] permissionIds) {
        Role role = new Role();
        role.setName(name);
        role.setDescription(description);
        Set<Permission> permissions = new HashSet<>();
        for (int i : permissionIds) {
            Permission permission = permissionService.findById(i);
            permissions.add(permission);
        }
        role.setPermissions(permissions);
        roleService.save(role);
        return roleService.findAll();
    }

    /**
     * 更新配置的权限
     *
     * @param permissionIds
     * @param response
     * @return
     */
    @PostMapping("/{id}/edit")
    public List<Role> update(@PathVariable Integer id,@RequestHeader String name,@RequestHeader String description,@RequestBody Integer[] permissionIds) {
        Role role = roleService.findById(id);
        role.setName(name);
        role.setDescription(description);
        Set<Permission> permissions = new HashSet<>();
        if(permissionIds != null) {
            for (int i : permissionIds) {
                Permission permission = permissionService.findById(i);
                permissions.add(permission);
            }
        }
        role.setPermissions(permissions);
        roleService.save(role);
        return roleService.findAll();
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}/delete")
    public List<Role> delete(@PathVariable Integer id) {
        roleService.deleteById(id);
        return roleService.findAll();
    }
}
