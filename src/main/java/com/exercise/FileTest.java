package com.exercise;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Auther: wy
 * @Date: 2019/8/6 23:21
 * @Description:
 */
public class FileTest {

    public static void main(String[] args) {
        File file = new File("d:/LOL.exe");
        try {
            System.out.println("试图打开这个文件");
            new FileInputStream(file);
        } catch (Exception e) {
            System.out.println("打开失败了");
            e.printStackTrace();
        }
    }
}
