package com.mapper;

import com.dto.OrderMealRecordSelectDTO;
import com.entity.OrderMealInfo;
import com.common.base.SuperMapper;
import com.vo.QueryOrderMealRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户订餐情况表 Mapper 接口
 * </p>
 *
 * @author wp
 * @since 2019-01-17
 */

public interface OrderMealInfoMapper extends SuperMapper<OrderMealInfo>{

    String selectRecord1();


   List analysisOrderMealRecord(OrderMealRecordSelectDTO orderMealRecordSelectDTO);


    List<QueryOrderMealRecordVO> QueryOrderMealRecord();
}