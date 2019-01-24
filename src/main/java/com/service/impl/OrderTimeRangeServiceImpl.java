package com.service.impl;

import com.dto.OrderTimeRangeDTO;
import com.dto.OrderTimeRangeForQueryDTO;
import com.entity.OrderMealInfo;
import com.entity.OrderTimeRange;
import com.mapper.OrderMealInfoMapper;
import com.mapper.OrderTimeRangeMapper;
import com.model.OrderMealInfoModel;
import com.model.OrderTimeRangeModel;
import com.service.IOrderMealInfoService;
import com.service.IOrderTimeRangeService;
import com.common.base.BaseServiceImpl;
import com.vo.AnalysisOrderMealRecordVO;
import com.vo.OrderTimeRangeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 餐别允许预定时间范围表 服务实现类
 * </p>
 *
 * @author wp
 * @since 2019-01-17
 */
@Service
@Slf4j
public class OrderTimeRangeServiceImpl extends BaseServiceImpl<OrderTimeRangeMapper, OrderTimeRange,OrderTimeRangeModel>  implements IOrderTimeRangeService {

    @Resource
    private OrderTimeRangeMapper orderTimeRangeMapper;

    @Override
    public void updateLunch(OrderTimeRangeDTO orderTimeRangeDTO) {
        log.info("开始修改餐别允许预定时间范围: {}",orderTimeRangeDTO);
        OrderTimeRange orderTimeRange = baseMapper.selectById(orderTimeRangeDTO.getMealType());
        orderTimeRange.setDelFlag("0");
        orderTimeRange.setStartTime(orderTimeRangeDTO.getStartTime());
        orderTimeRange.setEndTime(orderTimeRangeDTO.getEndTime());
        boolean b = updateById(orderTimeRange);

        log.info("修改后的餐别允许预定时间范围: {}",orderTimeRange);
        //:通过穿过来的数据找到当前要修改的餐别的数据

    }

    @Override
    public void logicDeleteOrderMealTimeRange(String mealType) {
        Map<String,Object> selectMap = new HashMap<>();
        selectMap.put("mealType",mealType);
        String mealTypeToChinese = "L".equals(mealType) ?"午餐":"晚餐";
        log.info("逻辑删除允许预定时间范围的餐别为: {}",mealTypeToChinese);
        orderTimeRangeMapper.logicDeleteOrderMealTimeRange(selectMap);
        log.info("逻辑删除:{}"+"的允许预定时间范围成功",mealTypeToChinese);
    }

    @Override
    public List<OrderTimeRangeVO> getTimeRange(OrderTimeRangeForQueryDTO orderTimeRangeForQueryDTO) throws Exception {
        log.info("开始查看餐别可以预定时间范围，接收的参数为: {}",orderTimeRangeForQueryDTO);
        List<OrderTimeRangeVO> orderTimeRangeVOList = orderTimeRangeMapper.getTimeRange(orderTimeRangeForQueryDTO);
        if(null == orderTimeRangeVOList || orderTimeRangeVOList.size() ==0 ){
            log.info("当前没有设定该餐别可以预定时间范围或者接收参数有误");
            return Collections.EMPTY_LIST;
        }
        for (OrderTimeRangeVO orderTimeRangeVO : orderTimeRangeVOList) {
            orderTimeRangeVO.setMealTypeToChinese("L".equals(orderTimeRangeVO.getMealType()) ?"午餐":"晚餐");
        }
        log.info("当前设定的餐别可以预定时间范围为:{}",orderTimeRangeVOList);
        return orderTimeRangeVOList;
    }
}
