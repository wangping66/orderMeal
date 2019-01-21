package com.mapper;

import com.dto.OrderTimeRangeDTO;
import com.entity.OrderTimeRange;
import com.common.base.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.UpdateProvider;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 * 餐别允许预定时间范围表 Mapper 接口
 * </p>
 *
 * @author wp
 * @since 2019-01-17
 */
@Mapper
public interface OrderTimeRangeMapper extends SuperMapper<OrderTimeRange> {


    void deleteOrderMealRecord();

}
