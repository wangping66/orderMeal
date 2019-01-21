package com.vo;



import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;


import java.util.Date;

@Data
@Accessors(chain = true)
public class AnalysisOrderMealRecordVO {

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date orderMealDate;

    private String mealType;

    private int number;
}
