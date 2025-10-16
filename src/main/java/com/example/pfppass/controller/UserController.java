package com.example.pfppass.controller;

import cn.dev33.satoken.stp.StpUtil;

import com.example.pfppass.common.result.Result;
import com.example.pfppass.pojo.dto.DriverInfoUpdateDTO;
import com.example.pfppass.pojo.dto.PasswordUpdateDTO;
import com.example.pfppass.pojo.dto.UserUpdateDTO;
import com.example.pfppass.pojo.entity.User;
import com.example.pfppass.pojo.vo.DriverInfoVO;
import com.example.pfppass.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户个人中心控制器
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "客运司机个人信息", description = "客运司机个人信息相关接口")
public class UserController {

    private final UserService userService;

    /**
     * 获取司机个人信息
     */
    @GetMapping("/profile")
    @Operation(summary = "获取司机个人信息", description = "获取当前登录司机的个人信息")
    public Result<DriverInfoVO> getDriverInfo() {
        // 从Sa-Token中获取当前用户ID
        Long userId = StpUtil.getLoginIdAsLong();

        User user = userService.getById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }

        DriverInfoVO vo = new DriverInfoVO();
        BeanUtils.copyProperties(user, vo);

        return Result.success(vo);
    }

    /**
     * 更新司机个人信息
     */
    @PutMapping("/profile")
    @Operation(summary = "更新司机个人信息", description = "更新当前登录司机的个人信息")
    public Result<String> updateDriverInfo(@Validated @RequestBody DriverInfoUpdateDTO dto) {
        // 从Sa-Token中获取当前用户ID
        Long userId = StpUtil.getLoginIdAsLong();

        User user = userService.getById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }

        // 使用现有的updateProfile方法
        UserUpdateDTO updateDTO = new UserUpdateDTO();
        updateDTO.setName(dto.getName());
        updateDTO.setGender(dto.getGender());
        updateDTO.setPhone(dto.getPhone());
        updateDTO.setCarNumber(dto.getCarNumber());
        updateDTO.setDriveExperience(dto.getDriveExperience());
        updateDTO.setAvatar(dto.getAvatar());

        try {
            userService.updateProfile(userId, updateDTO);
            return Result.success("更新成功");
        } catch (Exception e) {
            return Result.error("更新失败：" + e.getMessage());
        }
    }

    @PutMapping("/password")
    @Operation(summary = "修改密码")
    public Result<String> updatePassword(@Valid @RequestBody PasswordUpdateDTO dto) {
        try {
            Long userId = StpUtil.getLoginIdAsLong();
            userService.updatePassword(userId, dto);
            return Result.success("修改成功");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/account")
    @Operation(summary = "注销账户")
    public Result<String> deleteAccount() {
        try {
            Long userId = StpUtil.getLoginIdAsLong();
            userService.deleteAccount(userId);
            StpUtil.logout();
            return Result.success("账户已注销");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
