package com.enums;

import lombok.Getter;


/**
 * excel模版枚举类
 *
 **/
@Getter
public enum ExcelTemplateEnum implements Enum<String, String> {

    //导出订餐模板
    COMPANY_EXPORT("orderMealExport", "orderMeal_export.xlsx");
    private final String value;
    private final String label;

    ExcelTemplateEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

}
