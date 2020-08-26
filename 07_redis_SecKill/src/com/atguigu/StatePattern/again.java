package com.atguigu.StatePattern;

import redis.clients.jedis.Jedis;

public class again implements State {
    @Override
    public boolean choujiang(Jedis jedis) {
        System.out.println("孙子，你已经中过奖了");
        jedis.close();
        return false;
    }
}
