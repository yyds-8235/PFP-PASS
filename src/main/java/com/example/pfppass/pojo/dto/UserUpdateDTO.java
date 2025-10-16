package com.example.pfppass.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户信息更新DTO
 */
@Data
@Schema(description = "用户信息更新DTO")
public class UserUpdateDTO {

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "车牌号")
    private String carNumber;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别 (0: 未知, 1: 男, 2: 女)")
    private Integer gender;

    @Schema(description = "司机驾龄")
    private Integer driveExperience;
}
