package com.itliusir.module.security.permission.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itliusir.module.security.permission.entity.Permission;
import com.itliusir.module.security.permission.service.PermissionService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by itliusir
 */
@RestController
@RequestMapping("/admin/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 角色列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Permission> list() {
    	List<Permission> list = permissionService.findAllChildPermission();
        return list;
    }

    /**
     * 保存添加的权限
     *
     * @param pid
     * @param name
     * @param description
     * @param url
     * @return
     */
    @PostMapping("/add")
    public List save(@RequestHeader Integer pid,@RequestHeader String name,@RequestHeader String description,@RequestHeader String url) {
        Permission permission = new Permission();
        permission.setName(name);
        permission.setDescription(description);
        permission.setUrl(url);
        permission.setPid(pid == null ? 0 : pid);
        permissionService.save(permission);
        return permissionService.findAll();
    }
    /**
     * 更新权限
     *
     * @param id
     * @param pid
     * @param name
     * @param description
     * @param url
     * @return
     */
    @PostMapping("/{id}/edit")
    public List update(@PathVariable Integer id,@RequestHeader Integer pid,@RequestHeader String name,@RequestHeader String description,@RequestHeader String url) {
        Permission permission = permissionService.findById(id);
        permission.setName(name);
        permission.setDescription(description);
        permission.setUrl(url);
        permission.setPid(pid == null ? 0 : pid);
        permissionService.save(permission);
        return permissionService.findAll();
    }

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    
    @DeleteMapping("/{id}/delete")
    public List delete(@PathVariable Integer id, HttpServletResponse response) {
        permissionService.deleteById(id);
        return permissionService.findAll();
    }
}
