package com.example.pfppass.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 站点信息实体类
 */
@Data
@TableName("station")
public class Station implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 站点名字
     */
    @TableField("station_name")
    private String stationName;

    /**
     * 站点图片
     */
    @TableField("station_image")
    private String stationImage;

    /**
     * 站点电话
     */
    @TableField("station_phone")
    private String stationPhone;

    /**
     * 站点地址
     */
    @TableField("station_address")
    private String stationAddress;

    /**
     * 站点可接订单数量
     */
    @TableField("available_orders")
    private Integer availableOrders;

    /**
     * 站点状态 0-关闭 1-开启
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
