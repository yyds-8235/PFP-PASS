package com.example.pfppass.service;

import com.example.pfppass.common.page.PageDTO;
import com.example.pfppass.pojo.vo.StationVO;
import com.example.pfppass.pojo.vo.StationDetailVO;

/**
 * 站点信息服务接口
 */
public interface StationService {

    /**
     * 分页查询站点列表
     * 
     * @param current 当前页
     * @param size    每页大小
     * @return 分页结果
     */
    PageDTO<StationVO> getStationList(Long current, Long size);

    /**
     * 根据ID获取站点详细信息
     * 
     * @param id 站点ID
     * @return 站点详细信息
     */
    StationDetailVO getStationDetail(Long id);
}
