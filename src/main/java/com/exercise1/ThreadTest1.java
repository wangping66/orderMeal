package com.exercise1;

/**
 * @Auther: wy
 * @Date: 2019/9/2 21:30
 * @Description:
 */
public class ThreadTest1 extends Thread {

    public void run(){
        System.out.println("执行run方法");
    }

    public static void main(String[] args) {
        ThreadTest1 threadTest1 = new ThreadTest1();
        threadTest1.start();
        System.out.println("执行main方法");
    }
}
