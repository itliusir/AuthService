package com.itliusir.module.user.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itliusir.module.security.role.entity.Role;
import com.itliusir.util.Constants;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by itliusir
 */
@Entity
@Table(name = "auth_user")
public class User implements Serializable {

    private static final long serialVersionUID = 200796098159096559L;

    @Id
    @GeneratedValue
    private int id;

    //用户名
    @Column(unique = true, nullable = false)
    private String username;

    //密码
    @Column(nullable = false )
    @JsonIgnore
    private String password;

    //用户令牌
    private String token;
    
    //注册时间
    @Column(nullable = false)
    @JsonFormat(pattern = Constants.DATETIME_FORMAT)
    private Date inTime;

    //用户与角色的关联关系
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "auth_user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
}
