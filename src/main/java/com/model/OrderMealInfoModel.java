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
public class OrderMealInfoModel extends BaseEntity<OrderMealInfoModel> {

    private static final long serialVersionUID = 1L;
    /**
     * 订餐情况表id
     */

    @TableId(value = "meal_info_id")
    private String mealInfoId;
    /**
     * 预定日期
     */
    @TableField("order_meal_date")
    private Date orderMealDate;
    /**
     * 用户名称
     */
    @TableField("user_name")
    private String userName;
    /**
     * 餐别
     */
    @TableField("meal_type")
    private String mealType;
    /**
     * 抓拍人脸图像
     */
    @TableField("track_face")
    private String trackFace;

    /**
     * 预定时间
     */
    @TableField("order_time")
    private Date orderTime;

    @Override
    protected Serializable pkVal() {
        return this.mealInfoId;
    }
}
