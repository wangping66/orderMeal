package com.exercise1;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: wy
 * @Date: 2019/8/27 14:04
 * @Description:
 */
public class ArrayListTest {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>(10);
        System.out.println(stringList.size());
        for(int i=0;i<11;i++){
            stringList.add(String.valueOf(i));
        }
        System.out.println(stringList.size());//这个输出是11 ，为啥不是它扩容后的大小10+10*0.75=18啊？



    }
}
