package com.example.pfppass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pfppass.pojo.entity.Station;
import org.apache.ibatis.annotations.Mapper;

/**
 * 站点信息Mapper接口
 */
@Mapper
public interface StationMapper extends BaseMapper<Station> {
}
