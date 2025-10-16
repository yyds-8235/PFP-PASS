package com.example.pfppass.pojo.vo;

import lombok.Data;

/**
 * 站点信息VO
 */
@Data
public class StationVO {

    /**
     * 站点ID
     */
    private Long id;

    /**
     * 站点名字
     */
    private String stationName;

    /**
     * 站点图片
     */
    private String stationImage;

    /**
     * 站点电话
     */
    private String stationPhone;

    /**
     * 站点地址
     */
    private String stationAddress;

    /**
     * 站点可接订单数量
     */
    private Integer availableOrders;

    /**
     * 站点状态 0-关闭 1-开启
     */
    private Integer status;
}
