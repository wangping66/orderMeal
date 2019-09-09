package com.exercise;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Auther: wy
 * @Date: 2019/8/26 15:38
 * @Description:
 */
public class RefectTest {
    /**
    1: java在运行状态下，对于任意一个类，都能知道这个类的所有属性和方法，对于任意一个对象，都能调用他的任意一个属性和方法
    2:  实际上，我们创建的每一个类，也是一个实例对象，是Class类的实例对象
     */
    /**
     * 利用反射机制获取对象
     * 1：通过类名获取到该类的类对象的，再获取构造器，再通过构造器创建一个对象
     *
     */

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String className = "com.exercise.Refect";
        /*try {
            //获取Class类的实例对象三种方法之一

            Class<?> aClass = Class.forName(className);
            //获取Class类的实例对象三种方法之二
            Refect refect = new Refect();
            Class<? extends Refect> aClass1 = refect.getClass();
            //获取Class类的实例对象三种方法之三

            Class<Refect> refectClass = Refect.class;
            System.out.println(aClass == aClass1);
            System.out.println(aClass == refectClass);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/


        /*try {
            Class<?> aClass = Class.forName(className);
            Constructor<?>[] constructors = aClass.getConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println(constructor);
            }
            Constructor<?> constructor = aClass.getConstructor();
            try {
                Object object = constructor.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            Constructor<?> constructor1 = aClass.getConstructor(float.class);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }*/


        Class<?> aClass = Class.forName(className);
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(float.class);
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance(100);
        System.out.println(o);

    }

}
