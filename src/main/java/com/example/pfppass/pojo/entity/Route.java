package com.example.pfppass.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 专线信息实体类
 */
@Data
@TableName("route")
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 出发点
     */
    @TableField("departure_point")
    private String departurePoint;

    /**
     * 终点
     */
    @TableField("destination")
    private String destination;

    /**
     * 出发时间
     */
    @TableField("departure_time")
    private LocalTime departureTime;

    /**
     * 预计到达时间
     */
    @TableField("estimated_arrival_time")
    private LocalTime estimatedArrivalTime;

    /**
     * 专线状态 0-关闭 1-开启
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
