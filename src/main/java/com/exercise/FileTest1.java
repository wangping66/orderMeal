package com.exercise;

import java.io.File;

/**
 * @Auther: wy
 * @Date: 2019/8/6 23:46
 * @Description:
 */
public class FileTest1 {

    public static void main(String[] args) {
        // 绝对路径
        File f1 = new File("F:\\新建文本文档.txt");
        System.out.println("f1的绝对路径：" + f1.getAbsolutePath());
        // 相对路径,相对于工作目录，如果在eclipse中，就是项目目录
        File f2 = new File("LOL.exe");
        System.out.println("f2的绝对路径：" + f2.getAbsolutePath());

        // 把f1作为父目录创建文件对象
        File f3 = new File(f1, "新建文本文档.txt");

        System.out.println("f3的绝对路径：" + f3.getAbsolutePath());
    }
}
