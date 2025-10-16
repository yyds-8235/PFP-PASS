package com.example.pfppass.pojo.vo;

import lombok.Data;

import java.time.LocalTime;

/**
 * 今日专线VO
 */
@Data
public class TodayRouteVO {

    /**
     * 专线ID
     */
    private Long id;

    /**
     * 出发点
     */
    private String departurePoint;

    /**
     * 终点
     */
    private String destination;

    /**
     * 出发时间
     */
    private LocalTime departureTime;

    /**
     * 预计到达时间
     */
    private LocalTime estimatedArrivalTime;

    /**
     * 专线状态 0-关闭 1-开启
     */
    private Integer status;
}
