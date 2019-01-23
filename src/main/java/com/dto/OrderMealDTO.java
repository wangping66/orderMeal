package com.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

@Data
public class OrderMealDTO {

    private Long mealInfoId;
    /**
     * 预定日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
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
    @JsonFormat(pattern="HH:mm",timezone="GMT+8")
    private Date orderTime;





}
