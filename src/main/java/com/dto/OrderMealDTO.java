package com.dto;



import lombok.Data;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

@Data
public class OrderMealDTO {

    private Long mealInfoId;
    /**
     * 预定日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date orderMealDate;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 餐别
     */
    private String mealType;
    /**
     * 抓拍人脸图像
     */
    private String trackFace;
    /**
     * 预定时间
     */
    @JSONField(format = "HH:MM")
    private Date orderTime;





}
