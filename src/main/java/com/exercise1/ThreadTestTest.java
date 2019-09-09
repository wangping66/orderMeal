package com.exercise1;

import org.apache.poi.ss.formula.functions.T;

/**
 * @Auther: wy
 * @Date: 2019/9/2 21:48
 * @Description:
 */
public class ThreadTestTest {
    public static void main(String[] args) {


        ThreadTest2 threadTest2 = new ThreadTest2();
        threadTest2.setName("myThread");
        threadTest2.start();
        for (int i=0;i<10;i++) {

            try {
                int time2 = (int) Math.random() * 100;
                Thread.sleep(time2);
                System.out.println("主线程名称" + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
