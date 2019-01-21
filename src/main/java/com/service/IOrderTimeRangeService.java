package com.service;

import com.dto.OrderTimeRangeDTO;
import com.entity.OrderTimeRange;
import com.common.base.BaseService;
import com.model.OrderTimeRangeModel;

/**
 * <p>
 * 餐别允许预定时间范围表 服务类
 * </p>
 *
 * @author wp
 * @since 2019-01-17
 */

public interface IOrderTimeRangeService extends BaseService<OrderTimeRangeModel> {

    void updateLunch(OrderTimeRangeDTO orderTimeRangeDTO);
    void deleteOrderMealRecord();

}
