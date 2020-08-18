package com.atguigu.StatePattern;

public class notStart implements State {
    @Override
    public boolean choujiang() {
        System.out.println("抽奖没开始");
        return false;
    }
}
