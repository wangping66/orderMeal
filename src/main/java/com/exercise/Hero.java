package com.exercise;

/**
 * @Auther: wy
 * @Date: 2019/6/25 22:42
 * @Description:
 */
public class Hero {



    private String name;
    private float hp;
    private int moveSpeed;

    float armor; //护甲
    void keng(){
        System.out.println("哈哈，我坑队友了");
    }
    float getArmor(){
        return armor;
    }

    public static void main(String[] args) {
        byte b = 100;
        byte a = (byte) 128;
        //System.out.println(a);
        char c = 'm';
        char d = '国';
        System.out.println(c+"========"+d);
    }
}
