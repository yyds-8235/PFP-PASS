package com.example.pfppass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.example.pfppass.pojo.dto.PasswordUpdateDTO;
import com.example.pfppass.pojo.entity.User;
import com.example.pfppass.mapper.UserMapper;
import com.example.pfppass.pojo.dto.UserRegisterDTO;
import com.example.pfppass.pojo.dto.UserUpdateDTO;
import com.example.pfppass.service.UserService;
import com.example.pfppass.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public User register(UserRegisterDTO dto) {
        // 检查用户名是否已存在
        User existUser = getByUsername(dto.getUsername());
        if (existUser != null) {
            throw new RuntimeException("用户名已存在");
        }

        // 创建用户
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(PasswordUtil.simpleEncode(dto.getPassword()));
        user.setGender(0); // 默认未知
        userMapper.insert(user);

        // 返回新创建的用户对象
        return user;
    }

    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void updateProfile(Long userId, UserUpdateDTO dto) {
        User user = new User();
        user.setId(userId);
        if (dto.getName() != null) {
            user.setName(dto.getName());
        }
        if (dto.getPhone() != null) {
            user.setPhone(dto.getPhone());
        }
        if (dto.getCarNumber() != null) {
            user.setCarNumber(dto.getCarNumber());
        }
        if (dto.getDriveExperience() != null) {
            user.setDriveExperience(dto.getDriveExperience());
        }
        if (dto.getAvatar() != null) {
            user.setAvatar(dto.getAvatar());
        }
        if (dto.getGender() != null) {
            user.setGender(dto.getGender());
        }
        userMapper.updateById(user);
    }

    @Override
    public void updatePassword(Long userId, PasswordUpdateDTO dto) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        // 验证旧密码
        if (!PasswordUtil.simpleMatches(dto.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("旧密码错误");
        }

        // 更新密码
        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setPassword(PasswordUtil.simpleEncode(dto.getNewPassword()));
        userMapper.updateById(updateUser);
    }

    @Override
    public void deleteAccount(Long userId) {
        userMapper.deleteById(userId);
    }
}
