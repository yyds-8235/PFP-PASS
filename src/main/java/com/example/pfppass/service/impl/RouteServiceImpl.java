package com.example.pfppass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.pfppass.mapper.RouteMapper;
import com.example.pfppass.common.page.PageDTO;
import com.example.pfppass.pojo.vo.RouteVO;
import com.example.pfppass.pojo.entity.Route;
import com.example.pfppass.pojo.vo.TodayRouteVO;
import com.example.pfppass.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 专线信息服务实现类
 */
@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteMapper routeMapper;

    @Override
    public PageDTO<RouteVO> getTodayRouteList(Long current, Long size) {
        Page<Route> page = new Page<>(current, size);
        LambdaQueryWrapper<Route> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Route::getStatus, 1); // 只查询开启状态的专线
        queryWrapper.orderByAsc(Route::getDepartureTime);

        Page<Route> routePage = routeMapper.selectPage(page, queryWrapper);

        List<RouteVO> routeVOList = routePage.getRecords().stream()
                .map(route -> {
                    RouteVO dto = new RouteVO();
                    BeanUtils.copyProperties(route, dto);
                    return dto;
                })
                .collect(Collectors.toList());

        return new PageDTO<>(routePage.getCurrent(), routePage.getSize(),
                routePage.getTotal(), routeVOList);
    }

    @Override
    public List<TodayRouteVO> getTodayRoutes() {
        LambdaQueryWrapper<Route> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Route::getStatus, 1); // 只查询开启状态的专线
        queryWrapper.orderByAsc(Route::getDepartureTime);

        List<Route> routes = routeMapper.selectList(queryWrapper);

        return routes.stream()
                .map(route -> {
                    TodayRouteVO vo = new TodayRouteVO();
                    BeanUtils.copyProperties(route, vo);
                    return vo;
                })
                .collect(Collectors.toList());
    }
}
