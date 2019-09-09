package com.exercise1;

/**
 * @Auther: wy
 * @Date: 2019/9/2 22:47
 * @Description:
 */
public class RunableTest {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable1());
        thread.start();
    }
}
