package com.controller;


import com.common.PageResult;
import com.common.Result;
import com.dto.CompareCustomGateInfoDTO;
import com.dto.OrderMealDTO;
import com.dto.OrderMealRecordSelectDTO;
import com.entity.OrderMealInfo;
import com.service.IOrderMealInfoService;
import com.vo.AnalysisOrderMealRecordVO;
import com.vo.ImportExcelResultVO;
import com.vo.MealTypeDropdownVO;
import com.vo.QueryOrderMealRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.service.ResponseMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;

@Slf4j
@RestController
@RequestMapping("/orderMeal")
@Api(value = "/orderMeal", description = "订餐情况")
public class OrderMealController {

    @Autowired
    private IOrderMealInfoService iOrderMealInfoService;

    @Value("${localPicture.path}")
    private String localPicturePath;


    @ApiOperation(value = "根据摄像头传过来的图片时间保存为订餐记录")
    @PostMapping("/save")
    public Result saveOrderMealRecord(@RequestBody CompareCustomGateInfoDTO compareCustomGateInfoDTO) throws ParseException {
        OrderMealInfo result = iOrderMealInfoService.saveOrderMealRecord(compareCustomGateInfoDTO);
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
        PageResult<AnalysisOrderMealRecordVO> analysisOrderMealRecordVOPageResult = iOrderMealInfoService.analysisOrderMealRecord(orderMealRecordSelectDTO);
        return Result.builder().ok("统计订餐记录成功").data(analysisOrderMealRecordVOPageResult).total(analysisOrderMealRecordVOPageResult.getTotal()).build();

    }

    @ApiOperation(value = "订餐记录查询")
    @PostMapping("/query")
    public Result queryOrderMealRecord(@RequestBody OrderMealRecordSelectDTO orderMealRecordSelectDTO) {
        PageResult<QueryOrderMealRecordVO> queryOrderMealRecordVOPageResult = iOrderMealInfoService.queryOrderMealRecord(orderMealRecordSelectDTO);
        return Result.builder().ok("订餐记录查询成功").data(queryOrderMealRecordVOPageResult).total(queryOrderMealRecordVOPageResult.getTotal()).build();

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

    /**
     * 批量导入房屋
     *
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    /*@PostMapping("/import")
    @ApiOperation(value = "导入房屋数据")
    public ImportExcelResultVO importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            return (iOrderMealInfoService.importActualBuildingByExcel(file, request));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            System.out.println("失敗");
        }
    }*/


    @GetMapping("/getPicFromLocal")
    @ResponseBody
    @ApiOperation(value = "图片")
    public void getPicFromLocal(String path, HttpServletResponse response)
            throws Exception{
        //String filePath = localPicturePath;
        File file = new File(path);
        response.setContentType("image/jpeg");
        InputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf, 0, buf.length)) != -1) {
            out.write(buf, 0, len);
        }
        try {
            out.flush();
        } finally {
            out.close();
            in.close();
        }
    }

    public static void main(String[] args) {

        //测试1
        //ceshi 2
    }
}
