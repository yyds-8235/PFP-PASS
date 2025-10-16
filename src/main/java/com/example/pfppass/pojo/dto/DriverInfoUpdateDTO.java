package com.example.pfppass.pojo.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * 客运司机信息更新DTO
 */
@Data
public class DriverInfoUpdateDTO {

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 性别 0-女 1-男
     */
    private Integer gender;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    /**
     * 车牌号
     */
    @NotBlank(message = "车牌号不能为空")
    private String carNumber;

    /**
     * 司机驾龄
     */
    private Integer driveExperience;

    /**
     * 头像
     */
    private String avatar;
}
