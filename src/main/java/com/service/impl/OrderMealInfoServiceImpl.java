package com.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.PageResult;
import com.dto.OrderMealDTO;
import com.dto.OrderMealRecordSelectDTO;
import com.entity.OrderMealInfo;
import com.enums.ExcelTemplateEnum;
import com.enums.MealTypeEnum;
import com.mapper.OrderMealInfoMapper;
import com.mapper.OrderTimeRangeMapper;
import com.model.OrderMealInfoModel;
import com.service.DownloadService;
import com.service.IOrderMealInfoService;
import com.common.base.BaseServiceImpl;
import com.utils.ExcelUtils;
import com.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import com.constant.HttpResponseCode;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 用户订餐情况表 服务实现类
 * </p>
 *
 * @author wp
 * @since 2019-01-17
 */
@Service
@Slf4j
public class OrderMealInfoServiceImpl extends BaseServiceImpl<OrderMealInfoMapper, OrderMealInfo,OrderMealInfoModel>  implements IOrderMealInfoService {


    @Resource
    private OrderMealInfoMapper orderMealInfoMapper;

    @Resource
    private DownloadService downloadService;

    @Resource
    private OrderTimeRangeMapper orderTimeRangeMapper;

    @Override
    public OrderMealInfo saveOrderMealRecord(OrderMealDTO orderMealDTO) {
        //1：根据传过来的订餐时间判断为午餐还是晚餐

        log.info("开始根据传过来的订餐时间判断为午餐还是晚餐");
        Date orderTime = orderMealDTO.getOrderTime();
        String mealType = judgeMealType(orderTime);
        log.info("订餐类型为: {}",mealType);
        if(mealType == null){
            log.info("那这条记录就不会保存到数据库中,原因是订餐类型经过时间判断为: {}",mealType);
            return new OrderMealInfo().setMealType("出现这个表示添加订餐记录失败，原因是订餐时间不在预定范围");
        }
        OrderMealInfo orderMealInfo = new OrderMealInfo();
        BeanUtils.copyProperties(orderMealDTO,orderMealInfo);
        orderMealInfo.setMealType(mealType);
        log.info("开始保存订餐记录: {}",orderMealInfo);
        EntityWrapper ew = new EntityWrapper();
        ew.eq("order_meal_date",orderMealDTO.getOrderMealDate());
        ew.eq("user_name",orderMealDTO.getUserName());
        ew.eq("meal_type",orderMealInfo.getMealType());
        OrderMealInfo orderMealInfo1 = selectOne(ew);
        if(orderMealInfo1 == null){
            baseMapper.insert(orderMealInfo);
            log.info("这条记录已保存在数据库中，同时主键为: {}",orderMealInfo.getMealInfoId());
        }else {
            log.info("保存这条记录失败,原因是这样的数据已有了: {}",orderMealInfo1.toString());
            return new OrderMealInfo().setMealType("出现这个表示添加订餐记录失败,原因是这样的数据已有了");
        }
        return orderMealInfo;
    }
    @Override
    public MealTypeDropdownVO getMealTypeMap() {
        log.info("开始获取餐别类型下拉框");
        MealTypeDropdownVO mealTypeDropdownVO = new MealTypeDropdownVO();
        List list = new ArrayList();
        MealTypeVO mealTypeVO1 = new MealTypeVO();
        MealTypeVO mealTypeVO2 = new MealTypeVO();
        mealTypeVO1.setType(MealTypeEnum.L.getValue());
        mealTypeVO1.setName(MealTypeEnum.valueOf("L").getLabel());
        mealTypeVO2.setType(MealTypeEnum.D.getValue());
        mealTypeVO2.setName(MealTypeEnum.valueOf("D").getLabel());
        list.add(mealTypeVO1);
        list.add(mealTypeVO2);
        mealTypeDropdownVO.setMealTypeMap(list);
        log.info("返回给前端的餐别类型数据为：{}", list);

        return mealTypeDropdownVO;
    }

    @Override
    public PageResult<AnalysisOrderMealRecordVO> analysisOrderMealRecord(OrderMealRecordSelectDTO orderMealRecordSelectDTO) {
        log.info("统计订餐记录开始，接收的参数为: {}",orderMealRecordSelectDTO);
        PageResult<AnalysisOrderMealRecordVO> result = new PageResult<>();
        Map<String,Object> selectMap = new HashMap<>();
        selectMap.put("mealType",orderMealRecordSelectDTO.getMealType());
        selectMap.put("startTime",orderMealRecordSelectDTO.getStartTime());
        selectMap.put("endTime",orderMealRecordSelectDTO.getEndTime());
        List<AnalysisOrderMealRecordVO> analysisOrderMealRecordVOs;
        int total = orderMealInfoMapper.analysisOrderMealRecord(selectMap).size();
        log.info("根据上述参数查询的总记录条数为: {}",total);
        selectMap.put("isPage",orderMealRecordSelectDTO.getIsPage());
        selectMap.put("no",(orderMealRecordSelectDTO.getNo()-1)*orderMealRecordSelectDTO.getLimit());
        selectMap.put("limit",orderMealRecordSelectDTO.getLimit());
        analysisOrderMealRecordVOs = orderMealInfoMapper.analysisOrderMealRecord(selectMap);
        for (AnalysisOrderMealRecordVO analysisOrderMealRecordVO : analysisOrderMealRecordVOs) {
            analysisOrderMealRecordVO.setMealType("L".equals(analysisOrderMealRecordVO.getMealType()) ?"午餐":"晚餐");
        }
        result.setRecords(analysisOrderMealRecordVOs);
        result.setTotal(total);
        return result;
    }

    @Override
    public PageResult<QueryOrderMealRecordVO> queryOrderMealRecord(OrderMealRecordSelectDTO orderMealRecordSelectDTO) {
        log.info("查询订餐记录开始，接收的参数为: {}",orderMealRecordSelectDTO);
        PageResult<QueryOrderMealRecordVO> result = new PageResult<>();
        Map<String,Object> selectMap = new HashMap<>();
        selectMap.put("mealType",orderMealRecordSelectDTO.getMealType());
        selectMap.put("startTime",orderMealRecordSelectDTO.getStartTime());
        selectMap.put("endTime",orderMealRecordSelectDTO.getEndTime());
        selectMap.put("userName",orderMealRecordSelectDTO.getUserName());
        List<QueryOrderMealRecordVO> queryOrderMealRecordVOS;
        int total = orderMealInfoMapper.queryOrderMealRecord(selectMap).size();
        log.info("根据上述参数查询的总记录条数为: {}",total);
        selectMap.put("isPage",orderMealRecordSelectDTO.getIsPage());
        selectMap.put("no",(orderMealRecordSelectDTO.getNo()-1)*orderMealRecordSelectDTO.getLimit());
        selectMap.put("limit",orderMealRecordSelectDTO.getLimit());
        queryOrderMealRecordVOS = orderMealInfoMapper.queryOrderMealRecord(selectMap);
        for (QueryOrderMealRecordVO queryOrderMealRecordVO : queryOrderMealRecordVOS) {
            queryOrderMealRecordVO.setMealType("L".equals(queryOrderMealRecordVO.getMealType()) ?"午餐":"晚餐");
        }
        result.setRecords(queryOrderMealRecordVOS);
        result.setTotal(total);
        return result;
    }

    @Override
    public void export1(HttpServletRequest request, HttpServletResponse response, OrderMealRecordSelectDTO orderMealRecordSelectDTO) throws IOException, InvalidFormatException {
        SimpleDateFormat formatter = new SimpleDateFormat(HttpResponseCode.FORMAT_DATE);
        StringBuilder fileName = new StringBuilder("订餐信息").append(formatter.format(new Date())).append(".xlsx");
        formatter.applyPattern(HttpResponseCode.FORMAT_DATE);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream inputStream = ClassUtils.getDefaultClassLoader().getResourceAsStream(HttpResponseCode.FILE_TEMPLATE_PATH + HttpResponseCode.SLASH + ExcelTemplateEnum.COMPANY_EXPORT.getLabel());
        Workbook wb = WorkbookFactory.create(inputStream);

        Sheet sheet = wb.getSheetAt(0);
        CellStyle bodyStyle = ExcelUtils.getDefaultBodyStyle(sheet.getWorkbook());
        CellStyle dateStyle = ExcelUtils.getDefaultDatetimeStyle(sheet.getWorkbook());
        int line = 1;
        int colIndex = 0;
        Map<String,Object> selectMap = new HashMap<>();
        selectMap.put("mealType",orderMealRecordSelectDTO.getMealType());
        selectMap.put("startTime",orderMealRecordSelectDTO.getStartTime());
        selectMap.put("endTime",orderMealRecordSelectDTO.getEndTime());
        selectMap.put("isPage",orderMealRecordSelectDTO.getIsPage());
        selectMap.put("no",(orderMealRecordSelectDTO.getNo()-1)*orderMealRecordSelectDTO.getLimit());
        selectMap.put("limit",orderMealRecordSelectDTO.getLimit());
        List<AnalysisOrderMealRecordVO> analysisOrderMealRecordVOs = orderMealInfoMapper.analysisOrderMealRecord(selectMap);
        if (analysisOrderMealRecordVOs != null && analysisOrderMealRecordVOs.size() > 0) {
            for (AnalysisOrderMealRecordVO analysisOrderMealRecordVO : analysisOrderMealRecordVOs) {
                Row row = sheet.createRow(line++);
                downloadService.setBodyCellValue(row, colIndex++, formatter.format(analysisOrderMealRecordVO.getOrderMealDate()), bodyStyle, dateStyle);
                downloadService.setBodyCellValue(row, colIndex++, "L".equals(analysisOrderMealRecordVO.getMealType()) ?"午餐":"晚餐",bodyStyle, dateStyle);
                downloadService.setBodyCellValue(row, colIndex++, analysisOrderMealRecordVO.getNumber(), bodyStyle, dateStyle);
                colIndex = 0;
            }
        }
        wb.write(output);
        output.flush();
        output.close();
        ExcelUtils.export(request, response, output.toByteArray(), fileName.toString());
    }

    private String judgeMealType(Date orderTime) {
        //需要从数据库去获取每个餐别允许的订餐时间 并比较判别出订餐类型 TODO
        String mealType = null;
        List<OrderTimeRangeVO> timeRangeVOS = orderTimeRangeMapper.getTimeRange(null);
        if(timeRangeVOS != null && timeRangeVOS.size() >0){
            for (OrderTimeRangeVO orderTimeRangeVO : timeRangeVOS) {
                long startTime = orderTimeRangeVO.getStartTime().getTime();
                long endTime = orderTimeRangeVO.getEndTime().getTime();
                if (startTime <= orderTime.getTime() && orderTime.getTime() <= endTime) {
                    mealType = orderTimeRangeVO.getMealType();
                    break;
                }
            }
        }
        return mealType;
    }
}
