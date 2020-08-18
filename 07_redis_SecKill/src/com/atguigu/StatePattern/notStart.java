package com.atguigu.StatePattern;

import redis.clients.jedis.Jedis;

public class notStart implements State {
    @Override
    public boolean choujiang(Jedis jedis) {
        System.out.println("抽奖没开始");
        return false;
    }
}
