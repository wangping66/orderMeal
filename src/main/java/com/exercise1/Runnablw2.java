package com.exercise1;

/**
 * @Auther: wy
 * @Date: 2019/9/2 23:19
 * @Description:
 */
public class Runnablw2 implements Runnable {

    private int count = 5;

    @Override
    public void run() {

        while (count>0){
            count--;
            System.out.println("数字为："+count);
        }
    }
}
