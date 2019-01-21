package com.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.common.base.BaseEntity;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 餐别允许预定时间范围表
 * </p>
 *
 * @author wp
 * @since 2019-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("order_time_range")
public class OrderTimeRange extends BaseEntity<OrderTimeRange> {

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
