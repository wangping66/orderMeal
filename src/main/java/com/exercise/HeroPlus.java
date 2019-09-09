package com.exercise;

/**
 * @Auther: wy
 * @Date: 2019/8/26 17:04
 * @Description:
 */
public class HeroPlus {

    /**
     * 反射总结：
     * 1 我们平时创建的类也是一种实例对象，是类Class的实例对象
     * 从实例对象获取类对象的三种方法
     * (1):Calss.forName("全类名") 这种用的最多
     * (2):类.class
     * (3):类的对象.getClass();
     */

    public String name;
    public float hp;
    public int damage;
    public int id;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public HeroPlus(){

    }
    public HeroPlus(String string) {
        name =string;
    }

    @Override
    public String toString() {
        return "Hero [name=" + name + "]";
    }
    public boolean isDead() {
        // TODO Auto-generated method stub
        return false;
    }
    public void attackHero(HeroPlus h2) {
        System.out.println(this.name+ " 正在攻击 " + h2.getName());
    }
}
