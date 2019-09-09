package com.exercise;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auther: wy
 * @Date: 2019/8/26 17:06
 * @Description:
 */
public class HeroPlusTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        HeroPlus heroPlus = new HeroPlus();
        /*heroPlus.setName("wp");
        Class<? extends HeroPlus> aClass = heroPlus.getClass();
        Field field = aClass.getDeclaredField("name");
        try {
            field.set(heroPlus,"wpp");
            System.out.println(heroPlus.name);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/

        Class<? extends HeroPlus> aClass = heroPlus.getClass();
        Method method = aClass.getMethod("setName", String.class);
        method.invoke(heroPlus, "反射设置名称");
        System.out.println(heroPlus.getName());
    }
}
