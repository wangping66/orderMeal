package com.exercise1;

/**
 * @Auther: wy
 * @Date: 2019/8/27 16:58
 * @Description:
 */
public class CommandTestLiSi implements Command {
    @Override
    public String doOperation(String str) {
        return "策略模式的"+str;
    }
}
