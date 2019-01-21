package com.entity;

import com.baomidou.mybatisplus.enums.IdType;

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
 * 用户订餐情况表
 * </p>
 *
 * @author wp
 * @since 2019-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("order_meal_info")
public class OrderMealInfo extends BaseEntity<OrderMealInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 订餐情况表id
     */
    @TableId(value = "meal_info_id", type = IdType.AUTO)
    private Long mealInfoId;
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
    /**
     * 人员id
     */
    @TableField("person_id")
    private Integer personId;


    @Override
    protected Serializable pkVal() {
        return this.mealInfoId;
    }

}
