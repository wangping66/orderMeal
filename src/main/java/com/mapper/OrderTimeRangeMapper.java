package com.mapper;

import com.dto.OrderTimeRangeDTO;
import com.dto.OrderTimeRangeForQueryDTO;
import com.entity.OrderTimeRange;
import com.common.base.SuperMapper;
import com.vo.OrderTimeRangeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.UpdateProvider;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

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


    void logicDeleteOrderMealTimeRange(Map<String,Object> selectMap);

    List<OrderTimeRangeVO> getTimeRange(OrderTimeRangeForQueryDTO orderTimeRangeForQueryDTO);

}
