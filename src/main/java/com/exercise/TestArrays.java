package com.exercise;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Auther: wy
 * @Date: 2019/8/7 22:37
 * @Description:
 */
public class TestArrays {
    public static void main(String[] args) {
        ArrayList<Hero> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arrayList.add(new Hero());
        }
        Iterator<Hero> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println(arrayList.toString());
    }
}
