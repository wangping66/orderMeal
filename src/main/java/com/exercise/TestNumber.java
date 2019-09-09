package com.exercise;

/**
 * @Auther: wy
 * @Date: 2019/8/18 23:02
 * @Description:
 */
public class TestNumber {

    public static void main(String[] args) {

        swap(3,5);
    }

    public static void swap(int a,int b){
        System.out.println("之前："+a+"------"+b);
        int temp  = a;
        a = b;
        b = temp;
        System.out.println("之后："+a+"------"+b);

    }

    public static int fn(int n){
        if(n==20){
            return 1;
        }else if(n==21){
            return 2;
        }
        else {
            return fn(n-1)+fn(n-2);
        }
    }
    //访问控制级别由小到大
    //private default protected public
}
