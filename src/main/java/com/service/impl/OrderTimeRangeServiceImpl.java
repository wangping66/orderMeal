package com.service.impl;

import com.dto.OrderTimeRangeDTO;
import com.entity.OrderMealInfo;
import com.entity.OrderTimeRange;
import com.mapper.OrderMealInfoMapper;
import com.mapper.OrderTimeRangeMapper;
import com.model.OrderMealInfoModel;
import com.model.OrderTimeRangeModel;
import com.service.IOrderMealInfoService;
import com.service.IOrderTimeRangeService;
import com.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 餐别允许预定时间范围表 服务实现类
 * </p>
 *
 * @author wp
 * @since 2019-01-17
 */
@Service
public class OrderTimeRangeServiceImpl extends BaseServiceImpl<OrderTimeRangeMapper, OrderTimeRange,OrderTimeRangeModel>  implements IOrderTimeRangeService {

    @Resource
    private OrderTimeRangeMapper orderTimeRangeMapper;

    @Override
    public void updateLunch(OrderTimeRangeDTO orderTimeRangeDTO) {
        OrderTimeRange orderTimeRange = baseMapper.selectById(orderTimeRangeDTO.getMealType());
        orderTimeRange.setStartTime(orderTimeRangeDTO.getStartTime());
        orderTimeRange.setEndTime(orderTimeRangeDTO.getEndTime());
        updateById(orderTimeRange);
        //:通过穿过来的数据找到当前要修改的餐别的数据

    }

    @Override
    public void deleteOrderMealRecord() {
        orderTimeRangeMapper.deleteOrderMealRecord();
    }
}
