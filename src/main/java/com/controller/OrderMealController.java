package com.controller;


import com.common.Result;
import com.dto.OrderMealDTO;
import com.dto.OrderMealRecordSelectDTO;
import com.entity.OrderMealInfo;
import com.service.IOrderMealInfoService;
import com.vo.MealTypeDropdownVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/orderMeal")
@Api(value = "/orderMeal", description = "订餐情况")
public class OrderMealController {

    @Resource
    private IOrderMealInfoService iOrderMealInfoService;


    @ApiOperation(value = "根据摄像头传过来的图片时间保存为订餐记录")
    @PostMapping("/save")
    public Result saveOrderMealRecord(@RequestBody OrderMealDTO orderMealDTO) {
        OrderMealInfo result = iOrderMealInfoService.saveOrderMealRecord(orderMealDTO);
        return Result.builder().ok("保存为订餐记录成功").data(result).build();
    }
    /**
     * 测试数据库是否连通
     */

    @GetMapping("/get")
    public Result getOrderMealRecord() {
        String userName = iOrderMealInfoService.getOrderMealRecord();
        return Result.builder().ok("查询订餐记录成功").data(userName).build();
        //return Result.builder().ok("查询订餐记录成功").data("1").build();
    }

    @GetMapping("/getMealTypeList")
    @ApiOperation(value = "获取餐别类型下拉框")
    public Result<MealTypeDropdownVO> getMealTypeList() {
        return Result.builder().ok("获取餐别类型下拉框成功").data(iOrderMealInfoService.getMealTypeList()).build();

    }

    @ApiOperation(value = "统计订餐记录")
    @PostMapping("/analysis")
    public Result analysisOrderMealRecord(@RequestBody OrderMealRecordSelectDTO orderMealRecordSelectDTO) {
        return Result.builder().ok("查询订餐记录成功").data(iOrderMealInfoService.analysisOrderMealRecord(orderMealRecordSelectDTO)).build();

    }

    @ApiOperation(value = "订餐记录查询")
    @PostMapping("/query")
    public Result queryOrderMealRecord(@RequestBody OrderMealRecordSelectDTO orderMealRecordSelectDTO) {
        return Result.builder().ok("订餐记录查询成功").data(iOrderMealInfoService.queryOrderMealRecord(orderMealRecordSelectDTO)).build();

    }
}
