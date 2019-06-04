package com.service;

import com.common.PageResult;
import com.dto.CompareCustomGateInfoDTO;
import com.dto.OrderMealDTO;
import com.dto.OrderMealRecordSelectDTO;
import com.entity.OrderMealInfo;
import com.common.base.BaseService;
import com.model.OrderMealInfoModel;
import com.vo.AnalysisOrderMealRecordVO;
import com.vo.ImportExcelResultVO;
import com.vo.MealTypeDropdownVO;
import com.vo.QueryOrderMealRecordVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

/**
 * <p>
 * 用户订餐情况表 服务类
 * </p>
 *
 * @author wp
 * @since 2019-01-17
 */
public interface IOrderMealInfoService extends BaseService<OrderMealInfoModel>{


    OrderMealInfo saveOrderMealRecord(CompareCustomGateInfoDTO compareCustomGateInfoDTO) throws ParseException;

    /**
     * 获取餐别类型下拉框
     */
    MealTypeDropdownVO getMealTypeMap();

    PageResult<AnalysisOrderMealRecordVO> analysisOrderMealRecord(OrderMealRecordSelectDTO orderMealRecordSelectDTO);

    PageResult<QueryOrderMealRecordVO> queryOrderMealRecord(OrderMealRecordSelectDTO orderMealRecordSelectDTO);

    /**
     * 导出记录
     */
    void export1(HttpServletRequest request, HttpServletResponse response, OrderMealRecordSelectDTO orderMealRecordSelectDTO) throws Exception;

    //ImportExcelResultVO importActualBuildingByExcel(MultipartFile file, HttpServletRequest request) throws Exception;

}
