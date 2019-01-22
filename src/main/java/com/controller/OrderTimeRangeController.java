package com.controller;


import com.common.Result;
import com.dto.OrderMealDTO;
import com.dto.OrderTimeRangeDTO;
import com.dto.OrderTimeRangeForQueryDTO;
import com.entity.OrderMealInfo;
import com.service.IOrderTimeRangeService;
import com.vo.OrderTimeRangeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 餐别允许预定时间范围表 前端控制器
 * </p>
 *
 * @author wp
 * @since 2019-01-17
 */
@RestController
@RequestMapping("/orderTimeRange")
@Api(value = "/orderTimeRange", description = "订餐允许时间范围修改")
public class OrderTimeRangeController {


    @Autowired
    private IOrderTimeRangeService iOrderTimeRangeService;

    @ApiOperation(value = "修改餐别允许预定时间范围")
    @PostMapping("/updateLunch")
    public Result saveOrderMealRecord(@RequestBody OrderTimeRangeDTO orderTimeRangeDTO) {
        iOrderTimeRangeService.updateLunch(orderTimeRangeDTO);
        return Result.builder().ok("修改成功").data("success").build();

    }

    @ApiOperation(value = "删除餐别允许预定时间范围")
    @DeleteMapping("/deleteLunch/{mealType}")
    public Result logicDeleteOrderMealTimeRange(@PathVariable String mealType) {
        iOrderTimeRangeService.logicDeleteOrderMealTimeRange(mealType);
        return Result.builder().ok("删除餐别允许预定时间范围成功").data("success").build();

    }

    @ApiOperation(value = "查看餐别可以预定时间范围")
    @GetMapping("/getTimeRange")
    public Result getTimeRange(OrderTimeRangeForQueryDTO orderTimeRangeForQueryDTO) throws Exception {
        List<OrderTimeRangeVO> orderTimeRangeVOList = iOrderTimeRangeService.getTimeRange(orderTimeRangeForQueryDTO);
        return Result.builder().ok("查看成功").data(orderTimeRangeVOList).build();

    }

}

