package com.exercise1;

/**
 * @Auther: wy
 * @Date: 2019/9/2 23:20
 * @Description:
 */
public class Runnablw2Test {
    public static void main(String[] args) {
        Runnablw2 runnablw1 = new Runnablw2();
        Runnablw2 runnablw2 = new Runnablw2();
        Runnablw2 runnablw3 = new Runnablw2();
        Runnablw2 runnablw4 = new Runnablw2();
        Thread thread1 = new Thread(runnablw1);
        Thread thread2 = new Thread(runnablw2);
        Thread thread3 = new Thread(runnablw3);
        Thread thread4 = new Thread(runnablw4);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
