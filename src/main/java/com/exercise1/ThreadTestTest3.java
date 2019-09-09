package com.exercise1;

/**
 * @Auther: wy
 * @Date: 2019/9/2 22:28
 * @Description:
 */
public class ThreadTestTest3 {

    public static void main(String[] args) {
        ThreadTest3 t1 = new ThreadTest3(1);
        ThreadTest3 t2 = new ThreadTest3(2);

        ThreadTest3 t3 = new ThreadTest3(3);

        ThreadTest3 t4 = new ThreadTest3(4);

        ThreadTest3 t5 = new ThreadTest3(5);

        ThreadTest3 t6 = new ThreadTest3(6);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}
