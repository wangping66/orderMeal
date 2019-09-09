package com.exercise;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Auther: wy
 * @Date: 2019/8/16 21:42
 * @Description:
 */
public class TestIO2 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try{
            fileInputStream = new FileInputStream("F:\\测试IO\\测试1.txt");
            File targetFile  = new File("F:\\测试IO\\测试输出流.txt");
            fileOutputStream = new FileOutputStream(targetFile,true);
            byte[] bytes = new byte[1024];
            int n = fileInputStream.read(bytes);
            while (n !=-1){
                fileOutputStream.write(bytes,0,n);
                n=fileInputStream.read(bytes);
            }
        }catch (IOException e){
            System.out.println(e);
        }finally {
            try {
                fileOutputStream.close();
                fileInputStream.close();
            }catch (IOException e){
                System.out.println(e);
            }
        }

    }
}
