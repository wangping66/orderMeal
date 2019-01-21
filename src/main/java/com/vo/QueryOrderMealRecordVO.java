package com.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class QueryOrderMealRecordVO {

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date orderMealDate;

    private String trackFace;

    private String userName;

    private String mealType;
}
