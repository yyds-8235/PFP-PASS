package com.example.pfppass.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
@TableName("passenger_driver")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 车牌号
     */
    @TableField("car_number")
    private String carNumber;

    /**
     * 司机驾龄
     */
    @TableField("drive_experience")
    private Integer driveExperience;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 账户创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 信息最后更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
