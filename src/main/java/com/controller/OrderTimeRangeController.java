package com.controller;


import com.common.Result;
import com.dto.OrderMealDTO;
import com.dto.OrderTimeRangeDTO;
import com.entity.OrderMealInfo;
import com.service.IOrderTimeRangeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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

    @ApiOperation(value = "修改午餐允许预定时间范围")
    @PostMapping("/updateLunch")
    public Result saveOrderMealRecord(@RequestBody OrderTimeRangeDTO orderTimeRangeDTO) {
        iOrderTimeRangeService.updateLunch(orderTimeRangeDTO);
        return Result.builder().ok("修改成功").data("success").build();

    }

    @ApiOperation(value = "删除午餐允许预定时间范围")
    @DeleteMapping("/deleteLunch")
    public Result deleteOrderMealRecord() {
        iOrderTimeRangeService.deleteOrderMealRecord();
        return Result.builder().ok("删除成功").data("success").build();

    }

}

