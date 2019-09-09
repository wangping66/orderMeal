package com.exercise1;

/**
 * @Auther: wy
 * @Date: 2019/9/2 22:22
 * @Description:
 */
public class ThreadTest3 extends Thread {

    private int i;
    public ThreadTest3(int i){
        this.i = i;
    }
    @Override
    public void run(){

        System.out.println("当前数字为"+i);
    }
}
