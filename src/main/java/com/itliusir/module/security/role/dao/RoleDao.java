package com.itliusir.module.security.role.dao;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.itliusir.module.security.role.entity.Role;
import java.util.List;

/**
 * Created by itliusir
 */
@Repository
@CacheConfig(cacheNames = "roles")
public interface RoleDao extends JpaRepository<Role, Integer> {

    @Cacheable
    Role findOne(int id);

    @Cacheable
    List<Role> findAll();

    void delete(int id);
}
