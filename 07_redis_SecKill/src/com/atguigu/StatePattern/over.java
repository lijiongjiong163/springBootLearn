package com.atguigu.StatePattern;

import redis.clients.jedis.Jedis;

public class over implements State {
    @Override
    public boolean choujiang(Jedis jedis) {
        System.out.println("抽奖结束");
        return false;
    }
}
