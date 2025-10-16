package com.example.pfppass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.pfppass.mapper.StationMapper;
import com.example.pfppass.common.page.PageDTO;
import com.example.pfppass.pojo.vo.StationVO;
import com.example.pfppass.pojo.entity.Station;
import com.example.pfppass.pojo.vo.StationDetailVO;
import com.example.pfppass.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 站点信息服务实现类
 */
@RequiredArgsConstructor
@Service
public class StationServiceImpl implements StationService {

    private final StationMapper stationMapper;

    @Override
    public PageDTO<StationVO> getStationList(Long current, Long size) {
        Page<Station> page = new Page<>(current, size);
        LambdaQueryWrapper<Station> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Station::getStatus, 1); // 只查询开启状态的站点
        queryWrapper.orderByAsc(Station::getId);

        Page<Station> stationPage = stationMapper.selectPage(page, queryWrapper);

        List<StationVO> stationVOList = stationPage.getRecords().stream()
                .map(station -> {
                    StationVO dto = new StationVO();
                    BeanUtils.copyProperties(station, dto);
                    return dto;
                })
                .collect(Collectors.toList());

        return new PageDTO<>(stationPage.getCurrent(), stationPage.getSize(),
                stationPage.getTotal(), stationVOList);
    }

    @Override
    public StationDetailVO getStationDetail(Long id) {
        Station station = stationMapper.selectById(id);
        if (station == null) {
            return null;
        }

        StationDetailVO vo = new StationDetailVO();
        BeanUtils.copyProperties(station, vo);
        // 这里可以添加获取今日可接订单数量的逻辑
        vo.setTodayAvailableOrders(station.getAvailableOrders());

        return vo;
    }
}
