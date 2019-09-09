package com.exercise;

/**
 * @Auther: wy
 * @Date: 2019/8/26 19:08
 * @Description:
 */

import java.lang.reflect.InvocationTargetException;

/**
 * 反射总结：
 * 1 我们平时创建的类也是一种实例对象，是类Class的实例对象
 * 从实例对象获取类对象的三种方法
 * (1):Calss.forName("全类名") 这种用的最多
 * (2):类.class
 * (3):类的对象.getClass();
 * 2:通过反射获取对象的属性，方法
 *  (1):用上一步获取是类对象.getFile()
 */
public class RefectTest2 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        /*Refect refect1 = new Refect();
        Class<?> aClass = Class.forName("com.exercise.Refect");
        Class<Refect> refectClass = Refect.class;
        Refect refect = new Refect();
        Class<? extends Refect> aClass1 = refect.getClass();
        System.out.println(aClass == refectClass);
        System.out.println(aClass == aClass1);
        System.out.println("----------应该是2个true------------");

        *//*Field field = aClass.getField("name");
        field.setAccessible(true);
        field.set(refect1,"2019826");*//*
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            if("name".equals(field.getName())){
                field.setAccessible(true);
                field.set(refect1,"20198261936");
                System.out.println(refect1.getName());
                System.out.println(field.getName());
            }
        }
        Method setHp = aClass.getDeclaredMethod("setHp", float.class);
        setHp.setAccessible(true);
        setHp.invoke(refect1,8888);
        System.out.println(refect1.getName());
        System.out.println(refect1.getHp());*/
    }
}
