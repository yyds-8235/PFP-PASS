package com.example.pfppass.service;

import com.example.pfppass.pojo.dto.PasswordUpdateDTO;
import com.example.pfppass.pojo.entity.User;
import com.example.pfppass.pojo.dto.UserRegisterDTO;
import com.example.pfppass.pojo.dto.UserUpdateDTO;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 用户注册
     */
    User register(UserRegisterDTO dto);

    /**
     * 根据用户名查询用户
     */
    User getByUsername(String username);

    /**
     * 根据ID查询用户
     */
    User getById(Long id);

    /**
     * 更新用户信息
     */
    void updateProfile(Long userId, UserUpdateDTO dto);

    /**
     * 修改密码
     */
    void updatePassword(Long userId, PasswordUpdateDTO dto);

    /**
     * 注销账户
     */
    void deleteAccount(Long userId);
}
