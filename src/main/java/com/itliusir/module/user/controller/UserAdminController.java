package com.itliusir.module.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itliusir.module.security.role.entity.Role;
import com.itliusir.module.security.role.service.RoleService;
import com.itliusir.module.user.entity.User;
import com.itliusir.module.user.service.UserService;

import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by itliusir
 */
@RestController
@RequestMapping("/admin/user")
public class UserAdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 用户列表
     *
     * @param p
     * @param model
     * @return
     */
    @GetMapping("/list")
    public List<User> list() {
    	return userService.findAll();
    }

    /**
     * 保存配置用户的角色
     *
     * @param id
     * @return
     */
    @PostMapping("/{id}/setUserRoles")
    public List<User> saveRole(@PathVariable Integer id, @RequestBody Integer[] roleIds) {
        User user = userService.findById(id);
        Set<Role> roles = new HashSet<>();
        for (int i : roleIds) {
            Role role = roleService.findById(i);
            roles.add(role);
        }
        user.setRoles(roles);
        userService.save(user);
        return userService.findAll();
    }

}
