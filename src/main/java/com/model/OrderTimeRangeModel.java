package com.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.common.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class OrderTimeRangeModel extends BaseEntity<OrderTimeRangeModel> {

    private static final long serialVersionUID = 1L;
    /**
     * 餐别类型
     */
    @TableId("meal_type")
    private String mealType;
    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 截止时间
     */
    @TableField("end_time")
    private Date endTime;


    @Override
    protected Serializable pkVal() {
        return this.mealType;
    }
}
