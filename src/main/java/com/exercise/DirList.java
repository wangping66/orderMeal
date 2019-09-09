package com.exercise;

import org.apache.xmlbeans.impl.common.ReaderInputStream;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Reader;

/**
 * @Auther: wy
 * @Date: 2019/8/15 20:14
 * @Description:
 */
public class DirList {
    public static void main(String[] args) {
        //File path = new File("F:\\测试IO\\测试1.txt");//file类代表磁盘文件的本身对象 不具备读写的功能，描述文件属性
        File path = new File("F:\\测试IO\\");
        System.out.println(path.getAbsolutePath());
        System.out.println(path.length());
        System.out.println(path.getName());
        System.out.println(path.canRead());
        System.out.println(path.canWrite());
        System.out.println(path.exists());
        System.out.println(path.isDirectory());
        System.out.println(path.isFile());
        String[] list = path.list();

        for (String s : list) {
            System.out.println(s);
        }



    }
}
