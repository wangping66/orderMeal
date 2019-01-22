package com.mapper;

import com.dto.OrderMealRecordSelectDTO;
import com.entity.OrderMealInfo;
import com.common.base.SuperMapper;
import com.vo.QueryOrderMealRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户订餐情况表 Mapper 接口
 * </p>
 *
 * @author wp
 * @since 2019-01-17
 */

public interface OrderMealInfoMapper extends SuperMapper<OrderMealInfo>{

   List analysisOrderMealRecord(Map<String,Object> selectMap);

    List<QueryOrderMealRecordVO> queryOrderMealRecord(Map<String,Object> selectMap);
}