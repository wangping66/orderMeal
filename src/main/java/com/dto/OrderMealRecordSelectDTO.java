package com.dto;


import com.common.base.BaseParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(value = "查询订餐记录")
public class OrderMealRecordSelectDTO extends BaseParam {

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "订餐类别")
    private String mealType;
}
