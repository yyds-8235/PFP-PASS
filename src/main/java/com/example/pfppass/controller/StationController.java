package com.example.pfppass.controller;

import com.example.pfppass.common.result.Result;
import com.example.pfppass.common.page.PageDTO;
import com.example.pfppass.pojo.vo.StationVO;
import com.example.pfppass.pojo.vo.StationDetailVO;
import com.example.pfppass.service.StationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 站点信息控制器
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/station")
@Tag(name = "站点信息", description = "站点信息相关接口")
public class StationController {

    private final StationService stationService;

    /**
     * 获取站点列表（途顺接单）
     */
    @GetMapping("/list")
    @Operation(summary = "获取站点列表", description = "分页获取可接单的站点列表")
    public Result<PageDTO<StationVO>> getStationList(
            @Parameter(description = "当前页", example = "1") @RequestParam(defaultValue = "1") Long current,
            @Parameter(description = "每页大小", example = "10") @RequestParam(defaultValue = "10") Long size) {

        PageDTO<StationVO> result = stationService.getStationList(current, size);
        return Result.success(result);
    }

    /**
     * 获取站点详细信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取站点详细信息", description = "根据站点ID获取站点详细信息")
    public Result<StationDetailVO> getStationDetail(
            @Parameter(description = "站点ID", example = "1") @PathVariable Long id) {

        StationDetailVO result = stationService.getStationDetail(id);
        if (result == null) {
            return Result.error("站点不存在");
        }

        return Result.success(result);
    }
}
