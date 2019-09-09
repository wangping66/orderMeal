package com.exercise;

import java.util.Arrays;

/**
 * @Auther: wy
 * @Date: 2019/8/6 22:50
 * @Description:
 */
public class MathTest {

    public static void main(String[] args) {
        int a[] = new int[] { 18, 62, 68, 82, 65, 9 };
        System.out.println("排序之前 :");
        System.out.println(Arrays.toString(a));
        Arrays.sort(a);
        System.out.println("排序之后:");
        System.out.println(Arrays.toString(a));


    }
}
