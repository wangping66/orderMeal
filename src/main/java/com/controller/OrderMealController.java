package com.controller;


import com.common.Result;
import com.dto.OrderMealDTO;
import com.dto.OrderMealRecordSelectDTO;
import com.entity.OrderMealInfo;
import com.service.IOrderMealInfoService;
import com.vo.AnalysisOrderMealRecordVO;
import com.vo.MealTypeDropdownVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/orderMeal")
@Api(value = "/orderMeal", description = "订餐情况")
public class OrderMealController {

    @Autowired
    private IOrderMealInfoService iOrderMealInfoService;


    @ApiOperation(value = "根据摄像头传过来的图片时间保存为订餐记录")
    @PostMapping("/save")
    public Result saveOrderMealRecord(@RequestBody OrderMealDTO orderMealDTO) {
        OrderMealInfo result = iOrderMealInfoService.saveOrderMealRecord(orderMealDTO);
        return Result.builder().ok("保存为订餐记录成功").data(result).build();
    }

    @GetMapping("/getMealTypeMap")
    @ApiOperation(value = "获取餐别类型下拉框")
    public Result<MealTypeDropdownVO> getMealTypeMap() {
        return Result.builder().ok("获取餐别类型下拉框成功").data(iOrderMealInfoService.getMealTypeMap()).build();

    }

    @ApiOperation(value = "统计订餐记录")
    @PostMapping("/analysis")
    public Result analysisOrderMealRecord(@RequestBody OrderMealRecordSelectDTO orderMealRecordSelectDTO) {
        List<AnalysisOrderMealRecordVO> analysisOrderMealRecordVOS = iOrderMealInfoService.analysisOrderMealRecord(orderMealRecordSelectDTO);
        return Result.builder().ok("统计订餐记录成功").data(analysisOrderMealRecordVOS).build();

    }

    @ApiOperation(value = "订餐记录查询")
    @PostMapping("/query")
    public Result queryOrderMealRecord(@RequestBody OrderMealRecordSelectDTO orderMealRecordSelectDTO) {
        return Result.builder().ok("订餐记录查询成功").data(iOrderMealInfoService.queryOrderMealRecord(orderMealRecordSelectDTO)).build();

    }

    @GetMapping("/export")
    @ApiOperation(value = "导出记录")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        try {
            OrderMealRecordSelectDTO orderMealRecordSelectDTO = new OrderMealRecordSelectDTO();
            orderMealRecordSelectDTO.setStartTime(request.getParameter("startTime"));
            orderMealRecordSelectDTO.setEndTime(request.getParameter("endTime"));
            orderMealRecordSelectDTO.setMealType(request.getParameter("mealType"));
            iOrderMealInfoService.export1(request, response, orderMealRecordSelectDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
