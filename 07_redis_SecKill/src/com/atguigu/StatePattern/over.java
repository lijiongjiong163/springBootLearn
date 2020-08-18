package com.atguigu.StatePattern;

public class over implements State {
    @Override
    public boolean choujiang() {
        System.out.println("抽奖结束");
        return false;
    }
}
