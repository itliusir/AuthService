package com.itliusir.module.security.permission.dao;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.itliusir.module.security.permission.entity.Permission;
import java.util.List;

/**
 * Created by itliusir
 */
@Repository
@CacheConfig(cacheNames = "permissions")
public interface PermissionDao extends JpaRepository<Permission, Integer> {

    @Cacheable
    Permission findOne(int id);

    @Cacheable
    List<Permission> findByPidGreaterThan(int pid);

    List<Permission> findByPid(int pid);

    void deleteByPid(int pid);
}
