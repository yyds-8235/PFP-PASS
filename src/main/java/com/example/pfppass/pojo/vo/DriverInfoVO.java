package com.example.pfppass.pojo.vo;

import lombok.Data;

/**
 * 客运司机信息VO
 */
@Data
public class DriverInfoVO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别 0-女 1-男
     */
    private Integer gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 车牌号
     */
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
