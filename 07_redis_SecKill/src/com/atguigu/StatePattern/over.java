package com.atguigu.StatePattern;

import redis.clients.jedis.Jedis;

public class over implements State {
    @Override
    public boolean choujiang(Jedis jedis) {
        System.out.println("奖品没啦，抽奖结束");
        jedis.close();
        return false;
    }
}
