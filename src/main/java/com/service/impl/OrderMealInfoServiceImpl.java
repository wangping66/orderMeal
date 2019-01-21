package com.service.impl;

import com.dto.OrderMealDTO;
import com.dto.OrderMealRecordSelectDTO;
import com.entity.OrderMealInfo;
import com.enums.MealTypeEnum;
import com.mapper.OrderMealInfoMapper;
import com.model.OrderMealInfoModel;
import com.service.IOrderMealInfoService;
import com.common.base.BaseServiceImpl;
import com.vo.MealTypeDropdownVO;
import com.vo.QueryOrderMealRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户订餐情况表 服务实现类
 * </p>
 *
 * @author wp
 * @since 2019-01-17
 */
@Service
public class OrderMealInfoServiceImpl extends BaseServiceImpl<OrderMealInfoMapper, OrderMealInfo,OrderMealInfoModel>  implements IOrderMealInfoService {


    @Resource
    private OrderMealInfoMapper orderMealInfoMapper;

    @Override
    public OrderMealInfo saveOrderMealRecord(OrderMealDTO orderMealDTO) {
        //1：根据传过来的订餐时间判断为午餐还是晚餐


        Date orderTime = orderMealDTO.getOrderTime();

        OrderMealInfo orderMealInfo = new OrderMealInfo();
        BeanUtils.copyProperties(orderMealDTO,orderMealInfo);
        orderMealInfo.setMealType(orderMealDTO.getMealType());
        baseMapper.insert(orderMealInfo);
        return orderMealInfo;
    }

    @Override
    public String getOrderMealRecord() {
        return null;
    }

    @Override
    public MealTypeDropdownVO getMealTypeList() {
        MealTypeDropdownVO mealTypeDropdownVO = new MealTypeDropdownVO();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(MealTypeEnum.L.getValue(),MealTypeEnum.valueOf("L").getLabel());
        hashMap.put(MealTypeEnum.D.getValue(),MealTypeEnum.valueOf("D").getLabel());
        mealTypeDropdownVO.setMealTypeMap(hashMap);
        return mealTypeDropdownVO;
    }

    @Override
    public List analysisOrderMealRecord(OrderMealRecordSelectDTO orderMealRecordSelectDTO) {
        List list = orderMealInfoMapper.analysisOrderMealRecord(orderMealRecordSelectDTO);

        return list;
    }

    @Override
    public List<QueryOrderMealRecordVO> queryOrderMealRecord(OrderMealRecordSelectDTO orderMealRecordSelectDTO) {

        List<QueryOrderMealRecordVO> queryOrderMealRecordVOS = orderMealInfoMapper.QueryOrderMealRecord();
        return queryOrderMealRecordVOS;
    }

    private void judgeMealType(Date orderTime){
        //需要从数据库去获取每个餐别允许的订餐时间 并比较判别出订餐类型 TODO



    }
}
