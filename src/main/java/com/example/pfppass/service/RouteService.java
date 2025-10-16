package com.example.pfppass.service;

import com.example.pfppass.common.page.PageDTO;
import com.example.pfppass.pojo.vo.RouteVO;
import com.example.pfppass.pojo.vo.TodayRouteVO;

import java.util.List;

/**
 * 专线信息服务接口
 */
public interface RouteService {

    /**
     * 分页查询今日专线列表
     * 
     * @param current 当前页
     * @param size    每页大小
     * @return 分页结果
     */
    PageDTO<RouteVO> getTodayRouteList(Long current, Long size);

    /**
     * 获取今日专线列表（不分页）
     * 
     * @return 今日专线列表
     */
    List<TodayRouteVO> getTodayRoutes();
}
