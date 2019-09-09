package com.exercise1;

/**
 * @Auther: wy
 * @Date: 2019/9/2 21:44
 * @Description:
 */
public class ThreadTest2 extends Thread {
    @Override
    public void run() {


        for(int i=0;i<10;i++) {
            try {
                int time = (int) Math.random() * 1000;
                Thread.sleep(time);
                System.out.println("当前线程名称" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
