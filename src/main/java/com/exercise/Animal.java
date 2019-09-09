package com.exercise;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: wy
 * @Date: 2019/8/14 22:33
 * @Description:
 */
public class Animal {

    //定义一个实例属性
    private String name;

    //定义一个类属性
    public static String SName;

    public static void main(String[] args) {
        Map<Object,Object> hashMap = new HashMap<>();
        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(hashMap);
    }
}
