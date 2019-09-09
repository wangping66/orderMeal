package com.exercise;

/**
 * @Auther: wy
 * @Date: 2019/8/6 00:18
 * @Description:
 */
public class TestIf {

    public static void main(String[] args) {
        /*int a = 11;
        if(a == 6){
            System.out.println(a);
        }
        if(a == 7){
            System.out.println(a);
        }
        if(a == 8){
            System.out.println(a);
        }
        if(a == 9){
            System.out.println(a);
        }
        if(a ==10){
            System.out.println(a);
        }else if(a ==11){
            System.out.println(a);
        }else if(a== 12){
            System.out.println(a);
        }else {
            System.out.println("没找到这样的数");
        }
*/

        String str = "你好";
        switch (str){
            case "你":
                System.out.println("不对");
                break;
            case "好":
                System.out.println("不对");
                break;
            case "你好":
                System.out.println("对了");
                break;
            default:
                System.out.println("没有匹配成功");

        }


    }
}
