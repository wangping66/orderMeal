package com.exercise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Auther: wy
 * @Date: 2019/8/16 21:23
 * @Description:
 */
public class TestIO {

    public static void main(String[] args) {
        String path = "F:\\测试IO\\测试1.txt";
        try(FileInputStream fileInputStream = new FileInputStream(path)) {


            byte[] bytes = new byte[1024];
            int n =0;
            if((n= fileInputStream.read(bytes)) !=-1){
                String s=new String(bytes,0,n);    //将数组中从下标0到n的内容给s
                System.out.println(s);
            }
        } catch (Exception e) {
            if(e instanceof FileNotFoundException){
                System.out.println("文件找不到");
            }else if(e instanceof  IOException){
                System.out.println("文件输出流异常");
            }

        }
    }
}
