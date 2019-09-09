package com.exercise;

/**
 * @Auther: wy
 * @Date: 2019/8/5 23:09
 * @Description:
 */
public class StringTest {

    public static void main(String[] args) {


        System.out.println("-------------------------");
        StringBuffer stringBuffer1 = new StringBuffer("A");
        StringBuffer stringBuffer2 = new StringBuffer("A");
        System.out.println(stringBuffer1.equals(stringBuffer2));

        String s1 = "ABC";
        String s2  ="ABC";
        System.out.println(s1.equals(s2));//true
        System.out.println(s1 == s2);//true

        System.out.println("-+++++++++++++++++++++++++++=");
        Hero h1 = new Hero();
        Hero h2 = new Hero();
        System.out.println(h1 == h2);
        System.out.println(h1.equals(h2));
        /*
                1  基本数据类型只能用 ==比较字面值是否相等，用equals会报错
                2：超类Object 的equals是比较内存首地址，
                3: String 的== 比较的是内存首地址，equals()方法重写了，比较的是内容
                4：普通对象，==比较的是两个对象的内存首地址，不重写equals()下，比较的也是内存首地址
                5：StringBuffer 是个例外，它没有重写equals（）方法，==.equasl()比较的都是内存首地址
                6：直接String str1 = "ABC" String str2 = "ABC";
                这时候 == ，equas()都是true 比较的都是内存首地址
         */
    }
}
