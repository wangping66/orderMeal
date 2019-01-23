package com.service.impl;

import com.alibaba.fastjson.JSONArray;
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


        Date orderTime = orderMealDTO.getOrderTime();
        String mealType = judgeMealType(orderTime);
        if(mealType == null){
            return new OrderMealInfo().setMealType("出现这个表示添加订餐记录失败，原因是订餐时间不在预定范围");
        }
        OrderMealInfo orderMealInfo = new OrderMealInfo();
        BeanUtils.copyProperties(orderMealDTO,orderMealInfo);
        orderMealInfo.setMealType(mealType);
        baseMapper.insert(orderMealInfo);
        return orderMealInfo;
    }
    @Override
    public String getOrderMealRecord() {
        return null;
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
        log.info("根据上述参数查询结果为: {}",result);
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
        log.info("根据上述参数查询结果为: {}",result);
        return result;
    }

    /*@Override
    public Result export(OrderMealRecordSelectDTO orderMealRecordSelectDTO) {
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("sheet");

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        String []title = new String[]{"日期","餐别","订餐人数"};
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        return null;
    }
*/
    @Override
    public void export1(HttpServletRequest request, HttpServletResponse response, OrderMealRecordSelectDTO orderMealRecordSelectDTO) throws IOException, InvalidFormatException {
        SimpleDateFormat formatter = new SimpleDateFormat(HttpResponseCode.FORMAT_DATE);
        StringBuilder fileName = new StringBuilder("订餐信息").append(formatter.format(new Date())).append(".xlsx");
        formatter.applyPattern(HttpResponseCode.FORMAT_DATE);
        //QueryResults<CompanyListVO> resultList = getCompanyPageByFilters(null, dto);
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
        //List<CompanyListVO> companyListVOs = resultList.getResults();
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
