package com.itliusir.module.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itliusir.module.user.dao.UserDao;
import com.itliusir.module.user.entity.User;

/**
 * Created by itliusir
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findById(int id) {
        return userDao.findOne(id);
    }

    /**
     * 根据用户名判断是否存在
     *
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void updateUser(User user) {
        userDao.save(user);
    }

    /**
     * 查询用户列表
     * @return
     */
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 根据令牌查询用户
     * @param token
     * @return
     */
    public User findByToken(String token) {
        return userDao.findByToken(token);
    }
}
