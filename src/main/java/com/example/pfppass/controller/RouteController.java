package com.example.pfppass.controller;

import com.example.pfppass.common.result.Result;
import com.example.pfppass.common.page.PageDTO;
import com.example.pfppass.pojo.vo.RouteVO;
import com.example.pfppass.pojo.vo.TodayRouteVO;
import com.example.pfppass.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 专线信息控制器
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/route")
@Tag(name = "专线信息", description = "专线信息相关接口")
public class RouteController {

    private final RouteService routeService;

    /**
     * 获取今日专线列表（分页）
     */
    @GetMapping("/list")
    @Operation(summary = "获取今日专线列表", description = "分页获取今日专线列表")
    public Result<PageDTO<RouteVO>> getTodayRouteList(
            @Parameter(description = "当前页", example = "1") @RequestParam(defaultValue = "1") Long current,
            @Parameter(description = "每页大小", example = "10") @RequestParam(defaultValue = "10") Long size) {

        PageDTO<RouteVO> result = routeService.getTodayRouteList(current, size);
        return Result.success(result);
    }

    /**
     * 获取今日专线列表（不分页）
     */
    @GetMapping("/today")
    @Operation(summary = "获取今日专线列表", description = "获取今日专线列表（不分页）")
    public Result<List<TodayRouteVO>> getTodayRoutes() {
        List<TodayRouteVO> result = routeService.getTodayRoutes();
        return Result.success(result);
    }
}
