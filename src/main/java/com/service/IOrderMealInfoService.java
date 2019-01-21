package com.service;

import com.dto.OrderMealDTO;
import com.dto.OrderMealRecordSelectDTO;
import com.entity.OrderMealInfo;
import com.common.base.BaseService;
import com.model.OrderMealInfoModel;
import com.vo.MealTypeDropdownVO;
import com.vo.QueryOrderMealRecordVO;

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


    OrderMealInfo saveOrderMealRecord(OrderMealDTO orderMealDTO);

    String getOrderMealRecord();

    /**
     * 获取餐别类型下拉框
     */
    MealTypeDropdownVO getMealTypeList();

    List analysisOrderMealRecord(OrderMealRecordSelectDTO orderMealRecordSelectDTO);

    List<QueryOrderMealRecordVO> queryOrderMealRecord(OrderMealRecordSelectDTO orderMealRecordSelectDTO);
}
