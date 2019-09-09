package com.exercise;

/**
 * @Auther: wy
 * @Date: 2019/8/13 22:06
 * @Description:
 */
public class TestObject {


    public static void main(String[] args) {
        String str1 = "456,789,123,4564";
        String[] split = str1.split(",");
        for (String s : split) {
            System.out.println(s);
        }

        String str2 = "123";
        System.out.println(Integer.parseInt(str2));

    }
}
