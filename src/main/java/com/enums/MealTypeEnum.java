package com.enums;

import lombok.Getter;
import lombok.Setter;


public enum MealTypeEnum {

    L("L","午餐"),
    D("D","晚餐");
    @Getter
    @Setter
    private String value;

    @Getter
    @Setter
    private String label;

    MealTypeEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static MealTypeEnum getByLabel(String label){
        for (MealTypeEnum mealTypeEnum: MealTypeEnum.values()) {
            if (mealTypeEnum.getLabel().equals(label)) {
                return mealTypeEnum;
            }
        }
        return null;
    }

}
