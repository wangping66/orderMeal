package com.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class OrderTimeRangeDTO {
    /**
     * 订餐类别
     */
    private String mealType;
    /**
     * 开始时间
     */
    @JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
    private Date startTime;

    /**
     * 截止时间
     */
    @JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
    private Date endTime;
}
